package com.testapp.viewpager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.rd.PageIndicatorView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnAdd)
    ImageButton btnAdd;
    @BindView(R.id.btnRemove)
    ImageButton btnRemove;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btnPrev)
    ImageButton btnPrev;
    @BindView(R.id.btnNext)
    ImageButton btnNext;
    @BindView(R.id.imgEmpty)
    ImageView imgEmpty;

    private final List<Fragment> fragmentList = new ArrayList<>();
    TabPagerAdapter tabPagerAdapter;

    int currentPage = 0;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentList.add(new PageItemFragment().newInstance(Utality.getRandomAlphabet(),Utality.getRandomColor()));
        fragmentList.add(new PageItemFragment().newInstance(Utality.getRandomAlphabet(),Utality.getRandomColor()));
        fragmentList.add(new PageItemFragment().newInstance(Utality.getRandomAlphabet(),Utality.getRandomColor()));

        viewpager.setOffscreenPageLimit(fragmentList.size());
        setupViewPager(viewpager);

        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                pageControl();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        viewpager.setCurrentItem(0);
        currentPage = 0;

        pageControl();

    }

    private void pageControl() {
        if (currentPage == fragmentList.size() - 1) {
            btnNext.setVisibility(View.INVISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        } else if (currentPage == 0) {
            btnNext.setVisibility(View.VISIBLE);
            btnPrev.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        }

        if (fragmentList.size() <= 1) {
            btnNext.setVisibility(View.INVISIBLE);
            btnPrev.setVisibility(View.INVISIBLE);
        }

        if(fragmentList.size() == 0) {
            imgEmpty.setVisibility(View.VISIBLE);
        }else{
            imgEmpty.setVisibility(View.GONE);
        }

        showCurrentPage();
    }

    private void setupViewPager(ViewPager viewPager) {
        Timber.d("In SETUP");
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), fragmentList);
        pageIndicatorView.setCount(fragmentList.size());
        viewPager.setAdapter(tabPagerAdapter);
        tabPagerAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.btnAdd, R.id.btnRemove, R.id.btnPrev, R.id.btnNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                addPage();
                break;
            case R.id.btnRemove:
                removePage();
                break;
            case R.id.btnPrev:
                viewpager.setCurrentItem(--currentPage);
                tabPagerAdapter.notifyDataSetChanged();
                pageControl();
                break;
            case R.id.btnNext:
                viewpager.setCurrentItem(++currentPage);
                tabPagerAdapter.notifyDataSetChanged();
                pageControl();
                break;
        }
    }

    private void addPage() {
        int pos = currentPage + 1;
        if (fragmentList.size() > 0) {
            fragmentList.add(pos, new PageItemFragment().newInstance(Utality.getRandomAlphabet(),Utality.getRandomColor()));
        } else {
            fragmentList.add(new PageItemFragment().newInstance(Utality.getRandomAlphabet(),Utality.getRandomColor()));
        }
        tabPagerAdapter.clear();

        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(tabPagerAdapter);
        tabPagerAdapter.notifyDataSetChanged();
        viewpager.setCurrentItem(pos);
        pageControl();
    }


    private void removePage() {
        if(fragmentList.size() == 0) return;
        fragmentList.remove(viewpager.getCurrentItem());
        if (fragmentList.size() > 0) {
            tabPagerAdapter.clear();
            tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), fragmentList);
            viewpager.setAdapter(tabPagerAdapter);
            tabPagerAdapter.notifyDataSetChanged();
            viewpager.setCurrentItem(currentPage);
            pageControl();
        } else {
            currentPage = 0;
            imgEmpty.setVisibility(View.VISIBLE);
            pageControl();
        }
    }

    public void showCurrentPage() {
        //Toast.makeText(this, "Current Page : " + viewpager.getCurrentItem(), Toast.LENGTH_SHORT).show();
    }

}
