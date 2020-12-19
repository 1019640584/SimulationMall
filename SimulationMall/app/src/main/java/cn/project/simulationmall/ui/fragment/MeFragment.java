package cn.project.simulationmall.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.project.simulationmall.R;


/**
 * Create by ankele
 * <p>
 * 2020/9/30 - 22:04
 */
public class MeFragment extends Fragment {

    private static MeFragment instance = null;

    public static MeFragment getInstance() {
        if (instance == null) {
            instance = new MeFragment();
        }
        return instance;
    }

    private MeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        return view;
    }

}
