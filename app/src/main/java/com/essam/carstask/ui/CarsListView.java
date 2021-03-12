package com.essam.carstask.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.essam.carstask.R;
import com.essam.carstask.base.BaseActivity;
import com.essam.carstask.data.api.apiResponseDataClasses.CarsList;
import com.essam.carstask.utls.Resource;

import java.util.List;

public class CarsListView extends BaseActivity {

    ProgressBar progressBar;
    CarsListViewModel viewModel;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeContainer;
    RecyclerViewAdapter adapter = new RecyclerViewAdapter();
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_list_view);
        bindViews();
        setupRecyclerView();
        viewModel = new ViewModelProvider(this).get(CarsListViewModel.class);
        getData();
        showHideLoader(true,progressBar);
        handlePollToRefresh();
    }

    private void handlePollToRefresh() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                viewModel.reset();
                getData();
            }
        });
    }

    private void getData() {
        isLoading = true;
        viewModel.getCars().observe(this, new Observer<Resource<List<CarsList>>>() {
            public void onChanged(@Nullable Resource<List<CarsList>> carsList) {
                showHideLoader(false,progressBar);
                swipeContainer.setRefreshing(false);
                if(carsList.getStatus() == Resource.Status.SUCCESS){
                    if (carsList.getData() != null) {
                        adapter.insertData(carsList.getData());
                    }
                }else{
                    showErrorMessage(carsList.getErrorMsg());
                }
                isLoading = false;
            }
        });
    }

    void setupRecyclerView(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CarsListView.this));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (! recyclerView.canScrollVertically(1) && !isLoading){ //1 for down
                    showHideLoader(true,progressBar);
                    getData();
                }
            }
        });
    }


    void bindViews (){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
