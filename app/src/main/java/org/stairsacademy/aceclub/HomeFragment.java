package org.stairsacademy.aceclub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    DemoDatabase data = new DemoDatabase();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /*
        Get the listView in the activity_login.xml file.
        Use ArrayAdapter to translate the list of classes (data.classes) from the database
        for the listView (classList).
        Set adapter to listView.
        classActions(ListView listView) method is for actions when item in
        list is clicked.
        Action logic should be applies in the classActions(ListView listView)
        method.
        Source: https://www.youtube.com/watch?v=edZwD54xfbk
         */
        ListView classList = (ListView) view.findViewById(R.id.class_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data.classes);
        classList.setAdapter(adapter);
        classActions(classList);

        return view;
    }

    /*
    This method is for actions when an item from a list is clicked.
     */
    public void classActions(ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), data.classes.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

}
