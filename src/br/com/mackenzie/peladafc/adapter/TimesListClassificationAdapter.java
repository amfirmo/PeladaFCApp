package br.com.mackenzie.peladafc.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import br.com.mackenzie.peladafc.activity.R;
import br.com.mackenzie.peladafc.model.Jogador;
import br.com.mackenzie.peladafc.model.Time;

public class TimesListClassificationAdapter extends BaseExpandableListAdapter {

  private final List<Time> groups;
  public LayoutInflater inflater;
  public Activity activity;

  public TimesListClassificationAdapter(Activity act, List<Time> groups) {
    activity = act;
    this.groups = groups;
    inflater = act.getLayoutInflater();
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return groups.get(groupPosition).getEscalacao().get(childPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return 0;
  }

  @Override
  public View getChildView(int groupPosition, final int childPosition,
      boolean isLastChild, View convertView, ViewGroup parent) {
    final Jogador children = (Jogador) getChild(groupPosition, childPosition);
    TextView text = null;
    RatingBar rat = null;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.listrow_details_classification, null);
    }
    text = (TextView) convertView.findViewById(R.id.textView1);
    text.setText(children.getNome());
    
    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(activity, children.toString(),
            Toast.LENGTH_SHORT).show();
      }
    });
    
    rat = (RatingBar) convertView.findViewById(R.id.ratingBar1);
    rat.setRating(children.getClassificao());
    
    rat.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
      @Override
      public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){ 
        if(fromUser){
        	Toast.makeText(activity, children.getNome()+": NOTA: "+rating, Toast.LENGTH_SHORT).show();
        	//TODO change classificacao to float
        	children.setClassificao((int) rating);
        }
      }
    });
    
    return convertView;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return groups.get(groupPosition).getEscalacao().size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return groups.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return groups.size();
  }

  @Override
  public void onGroupCollapsed(int groupPosition) {
    super.onGroupCollapsed(groupPosition);
  }

  @Override
  public void onGroupExpanded(int groupPosition) {
    super.onGroupExpanded(groupPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return 0;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
      View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.listrow_group, null);
    }
    Time group = (Time) getGroup(groupPosition);
    ((CheckedTextView) convertView).setText(group.toString());
    ((CheckedTextView) convertView).setChecked(isExpanded);
    return convertView;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }
} 