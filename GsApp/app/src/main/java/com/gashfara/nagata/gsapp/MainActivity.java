package com.gashfara.nagata.gsapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupView(Context ctx) {
//        Get Window Size
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point windowSize = new Point();
        display.getSize(windowSize);
//        Set Color
        int black = ContextCompat.getColor(ctx, R.color.black);
        int yellow = ContextCompat.getColor(ctx, R.color.yellow);
        int white = ContextCompat.getColor(ctx, R.color.white);
        int gray = ContextCompat.getColor(ctx, R.color.gray);
//        motto
        TextView motto1 = (TextView) findViewById(R.id.motto1);
        motto1.setTextColor(black);
        TextView motto2 = (TextView) findViewById(R.id.motto2);
        motto2.setTextColor(black);
        TextView motto3 = (TextView) findViewById(R.id.motto3);
        motto3.setTextColor(black);
        TextView motto4 = (TextView) findViewById(R.id.motto4);
        motto4.setTextColor(black);
//        bg_cheese
        ImageView bgCheese = (ImageView) findViewById(R.id.bg_cheese);
        RelativeLayout.LayoutParams bgLayoutParams = (RelativeLayout.LayoutParams) bgCheese.getLayoutParams();
        bgLayoutParams.height = windowSize.x * 1061 / 1600;
//        catch_copy
        ImageView catchCopy = (ImageView) findViewById(R.id.catch_copy);
        RelativeLayout.LayoutParams catchCopyLayoutParams = (RelativeLayout.LayoutParams) catchCopy.getLayoutParams();
        catchCopyLayoutParams.width = (int) (windowSize.x * 0.8);
        catchCopyLayoutParams.height = catchCopyLayoutParams.width * 148 / 840;
//        news_title
        TextView titleNews = (TextView) findViewById(R.id.news_title);
        titleNews.setTextColor(yellow);
//        news_title_sub
        TextView subTitleNews = (TextView) findViewById(R.id.news_title_sub);
        subTitleNews.setTextColor(black);
//        news1
        TextView newsDate1 = (TextView) findViewById(R.id.news_date1);
        newsDate1.setTextColor(black);
        TextView newsContent1 = (TextView) findViewById(R.id.news_content1);
        newsContent1.setTextColor(black);
//        news2
        TextView newsDate2 = (TextView) findViewById(R.id.news_date2);
        newsDate2.setTextColor(black);
        TextView newsContent2 = (TextView) findViewById(R.id.news_content2);
        newsContent2.setTextColor(black);
//        news3
        TextView newsDate3 = (TextView) findViewById(R.id.news_date3);
        newsDate3.setTextColor(black);
        TextView newsContent3 = (TextView) findViewById(R.id.news_content3);
        newsContent3.setTextColor(black);
//        to_news_list
        TextView toNewsList = (TextView) findViewById(R.id.to_news_list);
        toNewsList.setTextColor(black);
//        feature_area
        LinearLayout featureArea = (LinearLayout) findViewById(R.id.feature_area);
        featureArea.setBackgroundColor(yellow);
//        feature_title
        TextView featureTitle = (TextView) findViewById(R.id.feature_title);
        featureTitle.setTextColor(white);
//        feature_title_sub
        TextView featureTitleSub = (TextView) findViewById(R.id.feature_title_sub);
        featureTitleSub.setTextColor(black);
//        points
        ImageView point1 = (ImageView) findViewById(R.id.point1);
        ImageView point2 = (ImageView) findViewById(R.id.point2);
        ImageView point3 = (ImageView) findViewById(R.id.point3);
        RelativeLayout.LayoutParams point1LayoutParams = (RelativeLayout.LayoutParams) point1.getLayoutParams();
        RelativeLayout.LayoutParams point2LayoutParams = (RelativeLayout.LayoutParams) point2.getLayoutParams();
        RelativeLayout.LayoutParams point3LayoutParams = (RelativeLayout.LayoutParams) point3.getLayoutParams();
        int pointWidth = (windowSize.x - 8 * 4) / 3 - 16;
        point1LayoutParams.width = pointWidth;
        point1LayoutParams.height = pointWidth;
        point2LayoutParams.width = pointWidth;
        point2LayoutParams.height = pointWidth;
        point3LayoutParams.width = pointWidth;
        point3LayoutParams.height = pointWidth;
//        course_title
        TextView courseTitle = (TextView) findViewById(R.id.course_title);
        courseTitle.setTextColor(black);
//        course_title_sub
        TextView courseTitleSub = (TextView) findViewById(R.id.course_title_sub);
        courseTitleSub.setTextColor(black);
//        course_lab
        int courseContentWidth = (windowSize.x - 8 * 4) / 2 - 24;
        ImageView courseLab = (ImageView) findViewById(R.id.course_lab);
        RelativeLayout.LayoutParams courseLabLayoutParams = (RelativeLayout.LayoutParams) courseLab.getLayoutParams();
        courseLabLayoutParams.width = courseContentWidth;
        courseLabLayoutParams.height = courseLabLayoutParams.width * 240 / 400;
//        lab_course_area
        LinearLayout labCourseArea = (LinearLayout) findViewById(R.id.lab_course_area);
        labCourseArea.getLayoutParams().width = courseContentWidth;
//        lab_course_title
        TextView labCourseTitle = (TextView) findViewById(R.id.lab_course_title);
        labCourseTitle.setTextColor(black);
//        lab_course_description
        TextView labCourseDescription1 = (TextView) findViewById(R.id.lab_course_description1);
        TextView labCourseDescription2 = (TextView) findViewById(R.id.lab_course_description2);
        TextView labCourseDescription3 = (TextView) findViewById(R.id.lab_course_description3);
        TextView labCourseDescription4 = (TextView) findViewById(R.id.lab_course_description4);
        TextView labCourseDescription5 = (TextView) findViewById(R.id.lab_course_description5);
        labCourseDescription1.setTextColor(black);
        labCourseDescription2.setTextColor(black);
        labCourseDescription3.setTextColor(black);
        labCourseDescription4.setTextColor(black);
        labCourseDescription5.setTextColor(black);
//        academy_course_area
        LinearLayout academyCourseArea = (LinearLayout) findViewById(R.id.academy_course_area);
        academyCourseArea.getLayoutParams().width = courseContentWidth;
//        academy_course_title
        TextView academyCourseTitle = (TextView) findViewById(R.id.academy_course_title);
        academyCourseTitle.setTextColor(black);
//        academy_course_description
        TextView academyCourseDescription1 = (TextView) findViewById(R.id.academy_course_description1);
        TextView academyCourseDescription2 = (TextView) findViewById(R.id.academy_course_description2);
        TextView academyCourseDescription3 = (TextView) findViewById(R.id.academy_course_description3);
        TextView academyCourseDescription4 = (TextView) findViewById(R.id.academy_course_description4);
        TextView academyCourseDescription5 = (TextView) findViewById(R.id.academy_course_description5);
        academyCourseDescription1.setTextColor(black);
        academyCourseDescription2.setTextColor(black);
        academyCourseDescription3.setTextColor(black);
        academyCourseDescription4.setTextColor(black);
        academyCourseDescription5.setTextColor(black);
//        course_academy
        ImageView courseAcademy = (ImageView) findViewById(R.id.course_academy);
        RelativeLayout.LayoutParams courseAcademyLayoutParams = (RelativeLayout.LayoutParams) courseAcademy.getLayoutParams();
        courseAcademyLayoutParams.width = courseContentWidth;
        courseAcademyLayoutParams.height = courseAcademyLayoutParams.width * 240 / 400;
//        gallery_area
        LinearLayout galleryArea = (LinearLayout) findViewById(R.id.gallery_area);
        galleryArea.setBackgroundColor(yellow);
//        gallery_title
        TextView galleryTitle = (TextView) findViewById(R.id.gallery_title);
        galleryTitle.setTextColor(black);
//        gallery_title_sub
        TextView galleryTitleSub = (TextView) findViewById(R.id.gallery_title_sub);
        galleryTitleSub.setTextColor(white);
//        gallery
        int galleryWidth = (windowSize.x - 8 * 5) / 4 - 16;
        int galleryHeight = galleryWidth * 120 / 160;
        ImageView gallery01 = (ImageView) findViewById(R.id.gallery01);
        gallery01.getLayoutParams().width = galleryWidth;
        gallery01.getLayoutParams().height = galleryHeight;
        ImageView gallery02 = (ImageView) findViewById(R.id.gallery02);
        gallery02.getLayoutParams().width = galleryWidth;
        gallery02.getLayoutParams().height = galleryHeight;
        ImageView gallery03 = (ImageView) findViewById(R.id.gallery03);
        gallery03.getLayoutParams().width = galleryWidth;
        gallery03.getLayoutParams().height = galleryHeight;
        ImageView gallery04 = (ImageView) findViewById(R.id.gallery04);
        gallery04.getLayoutParams().width = galleryWidth;
        gallery04.getLayoutParams().height = galleryHeight;
        ImageView gallery05 = (ImageView) findViewById(R.id.gallery05);
        gallery05.getLayoutParams().width = galleryWidth;
        gallery05.getLayoutParams().height = galleryHeight;
        ImageView gallery06 = (ImageView) findViewById(R.id.gallery06);
        gallery06.getLayoutParams().width = galleryWidth;
        gallery06.getLayoutParams().height = galleryHeight;
        ImageView gallery07 = (ImageView) findViewById(R.id.gallery07);
        gallery07.getLayoutParams().width = galleryWidth;
        gallery07.getLayoutParams().height = galleryHeight;
        ImageView gallery08 = (ImageView) findViewById(R.id.gallery08);
        gallery08.getLayoutParams().width = galleryWidth;
        gallery08.getLayoutParams().height = galleryHeight;
        ImageView gallery09 = (ImageView) findViewById(R.id.gallery09);
        gallery09.getLayoutParams().width = galleryWidth;
        gallery09.getLayoutParams().height = galleryHeight;
        ImageView gallery10 = (ImageView) findViewById(R.id.gallery10);
        gallery10.getLayoutParams().width = galleryWidth;
        gallery10.getLayoutParams().height = galleryHeight;
        ImageView gallery11 = (ImageView) findViewById(R.id.gallery11);
        gallery11.getLayoutParams().width = galleryWidth;
        gallery11.getLayoutParams().height = galleryHeight;
        ImageView gallery12 = (ImageView) findViewById(R.id.gallery12);
        gallery12.getLayoutParams().width = galleryWidth;
        gallery12.getLayoutParams().height = galleryHeight;
//        entry_area
        LinearLayout entryArea = (LinearLayout) findViewById(R.id.entry_area);
        entryArea.setBackgroundColor(yellow);
//        entry_title
        TextView entryTitle = (TextView) findViewById(R.id.entry_title);
        entryTitle.setTextColor(black);
//        entry_title_sub
        TextView entryTitleSub = (TextView) findViewById(R.id.entry_title_sub);
        entryTitleSub.setTextColor(white);
//        entries
        TextView name = (TextView) findViewById(R.id.name);
        TextView ruby = (TextView) findViewById(R.id.ruby);
        TextView email = (TextView) findViewById(R.id.email);
        TextView date = (TextView) findViewById(R.id.date);
        TextView motivation = (TextView) findViewById(R.id.motivation);
        int maxWidth = 336;
        name.setTextColor(black);
        name.getLayoutParams().width = maxWidth;
        ruby.setTextColor(black);
        ruby.getLayoutParams().width = maxWidth;
        email.setTextColor(black);
        email.getLayoutParams().width = maxWidth;
        date.setTextColor(black);
        date.getLayoutParams().width = maxWidth;
        motivation.setTextColor(black);
        motivation.getLayoutParams().width = maxWidth;
//        btn_entry
        ImageView btnEntry = (ImageView) findViewById(R.id.btn_entry);
        int btnEntryWidth = windowSize.x / 2;
        btnEntry.getLayoutParams().width = btnEntryWidth;
        btnEntry.getLayoutParams().height = btnEntryWidth * 90 / 399;
//        information_area
        LinearLayout informationArea = (LinearLayout) findViewById(R.id.information_area);
        informationArea.setBackgroundColor(gray);
//        information_title
        TextView informationTitle = (TextView) findViewById(R.id.information_title);
        informationTitle.setTextColor(white);
//        information_title_sub
        TextView informationTitleSub = (TextView) findViewById(R.id.information_title_sub);
        informationTitleSub.setTextColor(white);
//        information
        int informationWidth = (windowSize.x - 8 * 4) / 3 - 8;
        int informationHeight = informationWidth * 127 / 175;
        ImageView information1 = (ImageView) findViewById(R.id.information1);
        RelativeLayout.LayoutParams information1LayoutParams = (RelativeLayout.LayoutParams) information1.getLayoutParams();
        FrameLayout information2 = (FrameLayout) findViewById(R.id.information2);
        RelativeLayout.LayoutParams information2LayoutParams = (RelativeLayout.LayoutParams) information2.getLayoutParams();
        ImageView information3 = (ImageView) findViewById(R.id.information3);
        RelativeLayout.LayoutParams information3LayoutParams = (RelativeLayout.LayoutParams) information3.getLayoutParams();
        information1LayoutParams.width = informationWidth;
        information1LayoutParams.height = informationHeight;
        information2LayoutParams.width = informationWidth;
        information2LayoutParams.height = informationHeight;
        information3LayoutParams.width = informationWidth;
        information3LayoutParams.height = informationHeight;
//        map
        GoogleMapOptions options = new GoogleMapOptions();
        LatLng latLng = new LatLng(35.698584, 139.7663652);
        CameraPosition cameraPosition = new CameraPosition(latLng, 15, 0, 0);
        options.mapType(GoogleMap.MAP_TYPE_NORMAL)
                .camera(cameraPosition)
                .zoomControlsEnabled(true)
                .compassEnabled(false)
                .zoomGesturesEnabled(true)
                .scrollGesturesEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(false);
        MapFragment mMapFragment = MapFragment.newInstance(options);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.information2, mMapFragment);
        fragmentTransaction.commit();
//        caution
        TextView caution = (TextView) findViewById(R.id.caution);
        caution.setTextColor(white);
//        to_top
        ImageView toTop = (ImageView) findViewById(R.id.to_top);
        RelativeLayout.LayoutParams toTopLayoutParams = (RelativeLayout.LayoutParams) toTop.getLayoutParams();
        toTopLayoutParams.width = windowSize.x / 3;
        toTopLayoutParams.height = toTopLayoutParams.width * 111 / 141;
    }
}
