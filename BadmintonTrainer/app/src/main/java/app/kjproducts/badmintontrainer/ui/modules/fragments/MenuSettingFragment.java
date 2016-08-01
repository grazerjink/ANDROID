package app.kjproducts.badmintontrainer.ui.modules.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.kjproducts.badmintontrainer.R;
import app.kjproducts.badmintontrainer.ui.modules.LessonAdapter;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by graze on 31/07/2016.
 */
public class MenuSettingFragment extends BaseFragment {
    @Override
    protected String getTagName() {
        return "Menu";
    }

    private RecyclerView recyclerView;
    private LessonAdapter lessonAdapter;
    private List<String> lessons;

    void initLessons() {
        lessons = new ArrayList<>();
        lessons.add("Đang hoàn chỉnh Giao diện Menu...");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View advencedView = inflater.inflate(R.layout.basic_fragment,container,false);
        recyclerView = (RecyclerView) advencedView.findViewById(R.id.rec_Container);
        initLessons();
        lessonAdapter = new LessonAdapter(getActivity(),R.layout.item_card_view,lessons);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(lessonAdapter);
        alphaAdapter.setDuration(800);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleInAnimationAdapter.setDuration(800);
        scaleInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleInAnimationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return advencedView;
    }
}
