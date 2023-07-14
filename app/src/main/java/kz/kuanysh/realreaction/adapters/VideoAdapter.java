package kz.kuanysh.realreaction.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kz.kuanysh.realreaction.R;
import kz.kuanysh.realreaction.models.Video;

public class VideoAdapter extends BaseAdapter {

    private Context context;
    private List<Video> videoList;

    public VideoAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.thumbnailImageView = convertView.findViewById(R.id.thumbnail_image_view);
            viewHolder.titleTextView = convertView.findViewById(R.id.title_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Video video = videoList.get(position);

        // Set video title
        viewHolder.titleTextView.setText(video.getTitle());

        // Load thumbnail image using Picasso library
        Picasso.get()
                .load(video.getImageURL())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(viewHolder.thumbnailImageView);

        return convertView;
    }

    private static class ViewHolder {
        ImageView thumbnailImageView;
        TextView titleTextView;
    }
}
