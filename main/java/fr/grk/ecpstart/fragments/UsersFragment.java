package fr.grk.ecpstart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import fr.grk.ecpstart.R;
import fr.grk.ecpstart.activities.TweetsActivity;
import fr.grk.ecpstart.adapters.UsersAdapter;
import fr.grk.ecpstart.loaders.UsersLoader;
import fr.grk.ecpstart.model.AccountManager;
import fr.grk.ecpstart.model.User;

/**
 * Created by grk on 05/12/14.
 */
public class UsersFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<User>> {


    private static final int LOADER_USERS = 1000;

    private UsersAdapter mListAdapter;
    private boolean mIsMasterDetailsMode;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return new ListView(getActivity());
        //mListView = (ListView) inflater.inflate(R.layout.users_fragment, container, false);

        return inflater.inflate(R.layout.users_fragment, container, false);
    }

    //*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsMasterDetailsMode = (getActivity().findViewById(R.id.tweet_content) != null);
        mListAdapter = new UsersAdapter();
        setListAdapter(mListAdapter);
        if (mIsMasterDetailsMode){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

        //view.findViewById(R.id.po)

        view.findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });

    }
    //*/


    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(LOADER_USERS, null, this);
    }

    @Override
    public Loader<List<User>> onCreateLoader(int i, Bundle bundle) {
        return new UsersLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
        mListAdapter.setUsers(data);
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        User user = mListAdapter.getItem(position);

        if (! mIsMasterDetailsMode){
            Intent intent = new Intent(getActivity(), TweetsActivity.class);
            intent.putExtras(TweetsFragment.newArguments(user));
            startActivity(intent);
        }else {
            Fragment tweetFragment = new TweetsFragment();
            tweetFragment.setArguments(TweetsFragment.newArguments(user));
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.tweet_content, tweetFragment)
                    .commit();
        }
    }

    private void post(){
        if (AccountManager.isConnected(getActivity())){

        }else{
            new LoginFragment().show(getFragmentManager(), "login_dialog");
        }

    }
}
