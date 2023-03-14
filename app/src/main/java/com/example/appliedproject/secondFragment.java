package com.example.appliedproject;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class secondFragment extends Fragment implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private List<Item> items;
    public secondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_second);

        // use the inflater to inflate a layout file        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // create a new LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // add the inflated view to a ViewGroup in the activity' layout
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        GenerateItems();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(this, getActivity().getApplicationContext(), items));

        return view;

    }

    private void GenerateItems()
    {
        items = new ArrayList<Item>();
        items.add(new Item("Support", "Control your breath", "This means working against the natural urge of the diaphragm to release the air that has been inhaled. This is achieved by resisting its movement. During singing, the waist muscles and solar plexus are pushed outwards whilst the abdomen around the navel is gradually pulled in, in a constant and sustained manner, and the back muscles are tightened. The muscles in the loin try to pull the pelvis backwards, while the muscles in the abdomen try to pull the pelvis up under your body.", R.drawable.abdomen));





        items.add(new Item("Twang", "Make the sound lighter", "When twanging, the petiole (lower part of the epiglottis) and the arytenoids are getting closer. It creates a narrowing of the airflow that makes this kind of snarling sound \n" +
                "This narrowing creates an overpressure that makes it harder for the vocal cords to move but still we get the same volume and power. By that, protecting them from moving too wildly at loud volumes. Let’s try to find twang!\n" +
                "We can imitate a duck – try, how does it feel?\n" +
                "\n" +
                "Now let’s sing an EE, and gradually reduce the amount of twang. \n" +
                "\n" +
                "We all need a minimum amount of twang in order to produce a healthy note, this is called the necessary twang. \n \n", R.drawable.twang));




        items.add(new Item("The modes", "Get to know your voice","Neutral: is a non metallic mode, a kind of a soft sound [single note in Na]. This is a Neutral\n" +
                "note with air. We can also sing N without air: [single note in N]. It’s not that soft, but it’s still\n" +
                "soft compared to the metallic modes.\n" +
                "• Curbing: Curbing is a reduced metallic mode with a restrained character. [single note in C].\n" +
                "There is a kind of a hold on the sound[make a light hold on single note in C] . Compared to N\n" +
                "[single note in N then a single note in Curbing], Curbing is more metallic\n" +
                "• Overdrive: Overdrive is full metallic mode with a clear character. That means we can have a\n" +
                "lot of metal here. And [single note in Overdrive], and a lot of power.\n" +
                "• Edge: And then we have Edge, which is also full metallic mode with a clear character, can be\n" +
                "a bit more sharp [single note in Edge], a very loud sound.\n" +
                "• Sum up: So we have 1 non metallic, 1 reduced metallic, and 2 full metallic modes.\n" +
                "• Everybody have all the modes\n" +
                "We all have all modes. Some use certain modes more than others, but we all have them.\n" +
                "Some might not have worked with some modes for a long time and then it might seem like\n" +
                "that mode has disappeared in their voice, but it has not! The modes are always there and\n" +
                "can be trained if we want to use them.\n" +
                "• Each mode has an individual set of rules\n" +
                "Each of these modes has advantages and disadvantages.\n" +
                "Each of these modes has an individual set of rules we have to obey if we want to perform a\n" +
                "specific mode. So if we want to produce some loud singing we must choose a clear metallic\n" +
                "mode and we have to obey the Overdrive rules.\n" +
                "But if we use the Overdrive rules in Curbing, we will get into real trouble. And if we go to\n" +
                "Neutral then there will be another set of rules.\n" +
                "So the rules are very specific for each mode and when we obey them we can do whatever\n" +
                "we like. If we disobey them, we get in to trouble.", R.drawable.overview));



        items.add(new Item("The vowels", "How to pronounce in my own language?","Universal’ tongue positions for singing. We use ‘universal’ tongue positions for shaping\n" +
                "the vowels. They are displayed on the chart. (Show tongue/vowel chart) The chart is a\n" +
                "guideline for the general placing of the tongue in order to avoid tightening of the lips when\n" +
                "pronouncing the vowels, not depicting all anatomical variations of each tongue position. If we\n" +
                "choose these positions we can pronounce all sounds and avoid using the lips and protruding\n" +
                "the jaw, that might trigger uncontrolled constriction.\n" +
                "• Main vowels. We have 8 vowels we refer to as the main vowels.\n" +
                "• Back vowels: ‘OO’ like ‘too’ – ‘O’ like ‘woman’ – ‘OH’ like ‘so’ – and ‘Ah’ – like ‘far.’ (Sound\n" +
                "example and show on chart) We often use the lips for pronouncing these vowels.", R.drawable.all_vowels));



        items.add(new Item("Sound color", "How to change the way I sound?","All modes can be lightened or darkened, though some more than others. The sound colour is created in the vocal tract, which is the space above the vocal cords extending to the lips and including the nasal passages. The form and size of the vocal tract is of great importance to the sound colour. All singers have different vocal tracts, so all singers have their own personal sound colour. If the vocal tract is large, the sound colour will be darker. If it is small, the sound will be lighter. The shape of the vocal tract can be altered in many directions so there are many ways of changing the sound colour of your voice.", R.drawable.vocaltract));

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), explain_activity.class);
        intent.putExtra("TITLE", items.get(position).getTitle());
        intent.putExtra("SUBTITLE", items.get(position).getSubtitle());
        intent.putExtra("IMAGE", items.get(position).getImage());
        intent.putExtra("EXPLAIN", items.get(position).getInnerExplain());
        startActivity(intent);
    }    /*

    @Override    public void onItemClick(int position) {
        Intent.putExtra("Support", "Breathing techniques", R.drawable.support);        items.add(new Item("Twang", "Duck sound", R.drawable.twang));        items.add(new Item("The modes", "Know your voice", R.drawable.overview));        items.add(new Item("The vowels", "How to pronounce in my own language?", R.drawable.all_vowels));


    }*/}
