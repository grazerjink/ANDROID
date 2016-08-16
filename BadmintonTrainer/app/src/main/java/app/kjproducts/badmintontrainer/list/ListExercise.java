package app.kjproducts.badmintontrainer.list;

import com.bignerdranch.expandablerecyclerview.model.ParentListItem;

import java.util.List;

import app.kjproducts.badmintontrainer.model.Child;
import app.kjproducts.badmintontrainer.model.Exercise;

/**
 * Created by KJ on 13/08/2016.
 */
public class ListExercise extends Exercise implements ParentListItem {

    List<Child> childList;

    public ListExercise(long id_index, String title) {
        super(id_index, title);
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    @Override
    public List<Child> getChildItemList() {
        return childList;
    }


    public void setChildItemList(List<Child> list) {
        childList = list;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
