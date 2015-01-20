package fr.grk.ecpstart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.grk.ecpstart.R;
import fr.grk.ecpstart.model.User;

/**
 * Created by grk on 05/12/14.
 */
public class UsersAdapter extends BaseAdapter {

    private List<User> mUsers;


    public void setUsers(List<User> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }

    public List<User> getUsers() {
        return mUsers;
    }

    @Override
    public int getCount() {
        return (mUsers == null ? 0 : mUsers.size());
    }

    @Override
    public User getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (getCount() == 0 ) ? 0 : getItem(position).getId().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        }

        User user = getItem(position);
        TextView handleView = (TextView) convertView.findViewById(R.id.handle);
        handleView.setText(user.getHandle());

        /*
        TextView statusView = (TextView) convertView.findViewById(R.id.status);
        switch (user.getStatus()){
            case "online" : statusView.setText(R.string.onLine); break;
            case "offline" : statusView.setText(R.string.offLine); break;
                default:statusView.setText("");
        }
        //*/


        ImageView profilePictureView = (ImageView) convertView.findViewById(R.id.profile_picture);
        Picasso.with(convertView.getContext()).load(user.getPicture()).into(profilePictureView);
        return convertView;
    }

}
