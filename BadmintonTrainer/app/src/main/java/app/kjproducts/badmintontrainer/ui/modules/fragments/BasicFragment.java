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
public class BasicFragment extends BaseFragment {
    @Override
    protected String getTagName() {
        return "Basic Lesson";
    }

    private RecyclerView recyclerView;
    private LessonAdapter lessonAdapter;
    private List<String> lessons;

    void initLessons() {
        lessons = new ArrayList<>();
        lessons.add("1: Cách cầm vợt");
        lessons.add("2: Phát cầu ngang");
        lessons.add("3: Phát cầu từ dưới lên");
        lessons.add("4: Phát cầu nhẹ");
        lessons.add("5: Phát cầu cao,cuối sân");
        lessons.add("6: Phong cầu cao");
        lessons.add("7: Phong cầu thấp");
        lessons.add("8: Đập cầu cuối sân");
        lessons.add("9: Cách chạy cầu");
        lessons.add("10: Bỏ nhỏ");
        lessons.add("11: Chặt cầu");
        lessons.add("12: Cách di chuyển linh hoạt");
        lessons.add("13: Cách ve cầu");
        lessons.add("Đang cập nhật thêm...");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View basicView = inflater.inflate(R.layout.basic_fragment, container, false);
        recyclerView = (RecyclerView) basicView.findViewById(R.id.rec_Container);
        initLessons();
        lessonAdapter = new LessonAdapter(getActivity(), R.layout.item_card_view, lessons);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(lessonAdapter);
        alphaAdapter.setDuration(800);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleInAnimationAdapter.setDuration(800);
        scaleInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleInAnimationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return basicView;
    }
}
