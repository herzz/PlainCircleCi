package zahirherz.com.plaincircleci;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zahirherz.com.plaincircleci.model.Flower;

/**
 * Created by zahirh on 5/4/15.
 */
public class adapter extends ArrayAdapter<Flower> {
    String url = "http://services.hanselandpetal.com/photos/";
    private Context context;
    private List<Flower> flowerList;

    public adapter(Context context, int resource, List<Flower> objects) {
        super(context,resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_file,parent,false);
        Flower flower = flowerList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(flower.getName());
        ImageView img = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getContext()).load(url+flower.getPhoto()).resize(200,200).into(img);
        return view;
    }
}
