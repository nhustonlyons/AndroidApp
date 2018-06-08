package com.group4.group4;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PrivateKey;
import java.text.ParseException;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventAddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventAddFragment extends Fragment {

    private EditText editTxtName, editDescription;
    private TextView editDate;
    private Calendar mCurrentDate;
    private Button btnAdd;
    private Spinner mySpinner;
    private int day, month, year;
    private DatabaseReference databaseEvents;

    private OnFragmentInteractionListener mListener;

    public EventAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventAddFragment newInstance(String param1, String param2) {
        EventAddFragment fragment = new EventAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        databaseEvents = FirebaseDatabase.getInstance().getReference("events");

        editTxtName=(EditText)view.findViewById(R.id.event_name);
        editDescription=(EditText)view.findViewById(R.id.description);
        editDate=(TextView)view.findViewById(R.id.editDate);
        mySpinner=(Spinner)view.findViewById(R.id.location);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        month = month+1;

        editDate.setText(day+"/"+month+"/"+year);

        btnAdd=(Button)view.findViewById(R.id.addEvent);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        String monthString = "Jun";
                        switch(monthOfYear){
                            case 1:
                                monthString = "Jan";
                                break;
                            case 2:
                                monthString = "Feb";
                                break;
                            case 3:
                                monthString = "Mar";
                                break;
                            case 4:
                                monthString = "Apr";
                                break;
                            case 5:
                                monthString = "May";
                                break;
                            case 6:
                                monthString = "Jun";
                                break;
                            case 7:
                                monthString = "Jul";
                                break;
                            case 8:
                                monthString = "Aug";
                                break;
                            case 9:
                                monthString = "Sep";
                                break;
                            case 10:
                                monthString = "Oct";
                                break;
                            case 11:
                                monthString = "Nov";
                                break;
                            case 12:
                                monthString = "Dec";
                                break;
                        }
                        editDate.setText(monthString+"-"+dayOfMonth+"-"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                try {
                    addEvent();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;

    }

    public void addEvent() throws ParseException {
        String name = editTxtName.getText().toString().trim();
        String description = editDescription.getText().toString().trim();;
        int attendance = 0;
        float longitude = 0f;
        float latitude = 0f;

        switch(mySpinner.getSelectedItemPosition()){
            case 0:
                latitude=35.305941f;
                longitude=-80.732088f;
                break;
            case 1:
                latitude=35.305469f;
                longitude=-80.735480f;
                break;
            case 2:
                latitude=35.305519f;
                longitude=-80.728643f;
                break;
            case 3:
                latitude=35.305396f;
                longitude=-80.733158f;
                break;
            case 4:
                latitude=35.304848f;
                longitude=-80.731731f;
                break;
            case 5:
                latitude=35.301484f;
                longitude=-80.736438f;
                break;
            case 6:
                latitude=35.303090f;
                longitude=-80.732853f;
                break;
            case 7:
                latitude=35.308686f;
                longitude=-80.733737f;
                break;
            case 8:
                latitude=35.307065f;
                longitude=-80.34427f;
                break;
            case 9:
                latitude=35.302693f;
                longitude=-80.734796f;
                break;
        }

        String date = editDate.getText().toString();

        //String id = databaseEvents.push().getKey();
        Event event = new Event(name, description, 0, latitude, longitude, date);

        databaseEvents.child(name).setValue(event);
        Toast.makeText(getActivity(), "Event Added", Toast.LENGTH_SHORT).show();
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
