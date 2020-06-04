package vn.edu.ntu.vancuong.my_fragmentdemo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.vancuong.my_fragmentdemo.controller.IController;
import vn.edu.ntu.vancuong.my_fragmentdemo.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class layout2 extends Fragment implements View.OnClickListener {
    TextView txtGH;
    Button btnOk, btnCancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addView();

    }
    private void addView(){
        FragmentActivity activity = getActivity();
        txtGH = activity.findViewById(R.id.txtGH);

        btnOk = activity.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        btnCancel = activity.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        showShopping();
    }

    private void showShopping() {
        FragmentActivity activity = getActivity();
        IController iController = (IController) activity.getApplication();
        List<Product> products = iController.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for(Product p: products){
            builder.append(p.getName()).append("\t\t\t")
                    .append(p.getPrice()).append("\n");
        }
        if (builder.length()>0){
            txtGH.setText(builder.toString());

        }
        else
            txtGH.setText("khong co mat hang nao ca!!!");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnCancel:
                txtGH.setText("da huy don hang");
                Toast.makeText(getActivity(),"da huy don hang",Toast.LENGTH_SHORT).show();

            case R.id.btnOk:
                Toast.makeText(getActivity(),"mua thanh cong!",Toast.LENGTH_SHORT).show();

        }
    }
    private void deleteShopping(){
        FragmentActivity activity = getActivity();
        IController iController = (IController) activity.getApplication();
        iController.clearShoppingCart();
        txtGH.setText("khong co mat hang nao!!");
    }
}
