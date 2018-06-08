package com.group4.group4;

        import android.app.Activity;
        import android.app.Fragment;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.TileOverlay;
        import com.google.android.gms.maps.model.TileOverlayOptions;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.google.maps.android.heatmaps.HeatmapTileProvider;
        import com.google.maps.android.ui.IconGenerator;

        import java.util.ArrayList;

public class NowMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Event> events = Event.getEvents();
    private DatabaseReference databaseEvents = FirebaseDatabase.getInstance().getReference("events");

    public NowMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NowMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowMapFragment newInstance() {
        NowMapFragment fragment = new NowMapFragment();
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
        //events = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_map, container, false);

        MapFragment map = ((MapFragment) this.getChildFragmentManager().findFragmentById(R.id.map));
        map.getMapAsync(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnFragmentInteractionListener) activity;
        }
        catch (Exception e) {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(this);

        final IconGenerator iconGenerator = new IconGenerator(getActivity());
        iconGenerator.setColor(-5000);

        int size = events.size();

        Toast.makeText(getActivity(), Integer.toString(size), Toast.LENGTH_SHORT).show();

        for(Event event : events) {
            Bitmap icon = iconGenerator.makeIcon(event.getName());
            LatLng latLng = new LatLng(event.getLat(), event.getLng());
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(icon)).position(latLng).title(event.getName()));
        }

        databaseEvents.addValueEventListener(new ValueEventListener() {
            HeatmapTileProvider mHeatmap;
            ArrayList<LatLng> coordinates = new ArrayList<>();
            int size = coordinates.size();



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                events.clear();

                for(DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                    Event event = eventSnapshot.getValue(Event.class);
                    events.add(event);
                }

                for(Event event : events) {
                    Bitmap icon = iconGenerator.makeIcon(event.getName());
                    LatLng latLng = new LatLng(event.getLat(), event.getLng());
                    mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(icon)).position(latLng).title(event.getName()));
                }

                for(Event event : events) {
                    coordinates.clear();
                    if(event.getAttendance() >= 10 && event.getAttendance() <= 19) {
                        for(int i = 0; i < 10; i++) {
                            coordinates.add(new LatLng(event.getLat(), event.getLng()));
                        }

                        mHeatmap = new HeatmapTileProvider.Builder().data(coordinates).build();
                        mHeatmap.setRadius(75);
                        mHeatmap.setOpacity(.5);
                        TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mHeatmap));
                    }
                    else if(event.getAttendance() >= 20 && event.getAttendance() <= 49) {
                        for(int i = 0; i < 30; i++) {
                            coordinates.add(new LatLng(event.getLat(), event.getLng()));
                        }

                        mHeatmap = new HeatmapTileProvider.Builder().data(coordinates).build();
                        mHeatmap.setRadius(75);
                        mHeatmap.setOpacity(.75);
                        TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mHeatmap));
                    }
                    else if(event.getAttendance() >= 50 && event.getAttendance() <= 99) {
                        for(int i = 0; i < 50; i++) {
                            coordinates.add(new LatLng(event.getLat(), event.getLng()));
                        }

                        mHeatmap = new HeatmapTileProvider.Builder().data(coordinates).build();
                        mHeatmap.setRadius(75);
                        mHeatmap.setOpacity(1);
                        TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mHeatmap));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        LatLng uncc = new LatLng(35.307093, -80.733941);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uncc, 17f));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Intent intent = new Intent(getActivity(), EventDetails.class);
        intent.putExtra("eventName", marker.getTitle());
        startActivity(intent);

        return true;
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