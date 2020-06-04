package vn.edu.ntu.vancuong.my_fragmentdemo;


import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.vancuong.my_fragmentdemo.controller.IController;
import vn.edu.ntu.vancuong.my_fragmentdemo.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class layout1 extends Fragment {


    List<Product> listProduct;
    RecyclerView recyclerListProduct;
    ProductAdapter adapter;


    public layout1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout1, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        addView();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.txtGH:
                NavController navController = NavHostFragment.findNavController(layout1.this);
                navController.navigate(R.id.action_layout1_to_layout2);
                break;
            case R.id.Exit:
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void addView(){
        FragmentActivity activity = getActivity();
        recyclerListProduct = activity.findViewById(R.id.rvListProduct);
        recyclerListProduct.setLayoutManager(new LinearLayoutManager(activity));
        IController iController = (IController) activity.getApplication();
        listProduct = iController.getAllProduct();
        adapter = new ProductAdapter(listProduct);
        recyclerListProduct.setAdapter(adapter);
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName, txtPrice, txtDesc;
        ImageView imCart;
        Product p;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            imCart = itemView.findViewById(R.id.imcart);

            imCart.setOnClickListener(this);
        }
        public void bind(Product p){
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
        }

        @Override
        public void onClick(View view) {
            IController iController = (IController) getActivity().getApplication();
            if (iController.addtoCart(p)){
                Toast.makeText(getActivity(), p.getName()+"đã thêm vào mặt hàng",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getActivity(), p.getName()+"đã có ở giỏ hàng",Toast.LENGTH_SHORT).show();

        }
    }
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{

        List<Product> products = new ArrayList<>();

        public ProductAdapter(List<Product> products) {
            this.products = products;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product,parent,false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(products.get(position));
        }

        @Override
        public int getItemCount() {
            return products.size();
        }
    }

}
