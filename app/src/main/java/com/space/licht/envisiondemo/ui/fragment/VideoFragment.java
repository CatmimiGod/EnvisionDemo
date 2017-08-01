package com.space.licht.envisiondemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.space.licht.envisiondemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.space.licht.envisiondemo.R.id.lv_store;


/**
 * Description: MemberFragment
 */
public class VideoFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "VideoFragment";
    @BindView(lv_store)
    ListView mLvStore;
    private ArrayList<Store> mList;
    private StoreAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View mView = inflater.inflate(R.layout.fragment_video_view, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataI();
        initViewI();

    }

    /**
     * 初始化数据
     */
    private void initDataI() {
        mList = new ArrayList<Store>();
        Store store = new Store();
        store.setId(0);
        store.setName("店铺" + 0);

        List<Product> plist = new ArrayList<Product>();

        for (int j = 0; j < 5; j++) {
            Product info = new Product();
            info.setId(j);
            info.setPrice(j + 1);
            info.setContent("店铺中的商品" + 12 + j);
            info.setQuantity(1);
            plist.add(info);
        }
        store.setProducts(plist);

        mList.add(store);
    }

    /**
     * 初始化控件
     */
    private void initViewI() {
        mAdapter = new StoreAdapter(getContext(), mList);

        mLvStore.setAdapter(mAdapter);
        mLvStore.setOnItemClickListener(this);
        mLvStore.setEnabled(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
