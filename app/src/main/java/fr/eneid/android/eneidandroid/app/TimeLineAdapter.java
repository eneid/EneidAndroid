package fr.eneid.android.eneidandroid.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

public class TimeLineAdapter extends ArrayAdapter<Message> {

    private final Context context;
    private final List<Message> messages;
    private ImageView pic;


    public TimeLineAdapter(Context context, List<Message> messages) {
        super(context, R.layout.row_layout, messages);
        this.context = context;
        this.messages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message tMessage = messages.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.row_layout, parent, false);

        pic = (ImageView) rowLayout.findViewById(R.id.pic);
        TextView message = (TextView) rowLayout.findViewById(R.id.message);
        TextView name = (TextView) rowLayout.findViewById(R.id.name);

        new RetreivePictures().execute(tMessage.getPicUrl());

        message.setText(tMessage.getContent());
        name.setText(tMessage.getName());
        return rowLayout;
    }



    private class RetreivePictures extends AsyncTask<String, Void, Bitmap> {

        private Exception exception;

        protected Bitmap doInBackground(String... urls) {
            try {
                URL url= new URL(urls[0]);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return bitmap;
            } catch (Exception e) {
                Log.e("RetreivePictures", e.toString());
                return null;
            }
        }

        protected void onPostExecute(Bitmap bitmap) {
            pic.setImageBitmap(getRoundedShape(bitmap));
        }

        public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
            int targetWidth = 50;
            int targetHeight = 50;
            Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                    targetHeight,Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(targetBitmap);
            Path path = new Path();
            path.addCircle(((float) targetWidth - 1) / 2,
                    ((float) targetHeight - 1) / 2,
                    (Math.min(((float) targetWidth),
                            ((float) targetHeight)) / 2),
                    Path.Direction.CCW);

            canvas.clipPath(path);
            Bitmap sourceBitmap = scaleBitmapImage;
            canvas.drawBitmap(sourceBitmap,
                    new Rect(0, 0, sourceBitmap.getWidth(),
                            sourceBitmap.getHeight()),
                    new Rect(0, 0, targetWidth, targetHeight), null);
            return targetBitmap;
        }
    }
}
