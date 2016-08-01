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
public class EnlargedFragment extends BaseFragment {
    @Override
    protected String getTagName() {
        return "Enlarged Lesson";
    }

    private RecyclerView recyclerView;
    private LessonAdapter lessonAdapter;
    private List<String> lessons;

    void initLessons() {
        lessons = new ArrayList<>();
        lessons.add("Cách chọn vợt phù hợp");
        lessons.add("Chiến thuật khi đánh đơn");
        lessons.add("Quy luật trong trận đánh");
        lessons.add("Chiến thuật phòng thủ");
        lessons.add("Chiến thuật đánh cầu 4 điểm rồi đột kích");
        lessons.add("Tập chạy bền - nâng cao sức trâu bò");
        lessons.add("Đang cập nhật thêm....");
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
