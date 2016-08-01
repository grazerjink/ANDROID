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
public class AdvancedFragment extends BaseFragment {
    @Override
    protected String getTagName() {
        return "Advanced Lesson";
    }

    private RecyclerView recyclerView;
    private LessonAdapter lessonAdapter;
    private List<String> lessons;

    void initLessons() {
        lessons = new ArrayList<>();
        lessons.add("NC1: Phân tích chiến lược");
        lessons.add("NC2: Phát cầu trái tay");
        lessons.add("NC3: Phong cầu trái tay");
        lessons.add("NC4: Ve cầu hai bên lưới");
        lessons.add("NC5: Đập cầu trái tay");
        lessons.add("NC6: Đập cầu góc sân");
        lessons.add("NC7: Bỏ nhỏ nghịch tay");
        lessons.add("NC8: Cách đẩy cầu");
        lessons.add("NC9: Nâng cao cách di chuyển trong sân");
        lessons.add("Sẽ cập nhật thêm.....");
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
