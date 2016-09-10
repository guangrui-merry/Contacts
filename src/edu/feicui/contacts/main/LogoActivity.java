package edu.feicui.contacts.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import edu.feicui.contacts.R;

/**
 * 欢迎界面
 *
 */
public class LogoActivity extends Activity{
	private Animation animation;
	private ImageView img_logo;
	private AnimationListener animationListener = new AnimationListener(){
		//动画开始
		@Override
		public void onAnimationStart(Animation animation) {}

		//动画重复
		@Override
		public void onAnimationRepeat(Animation animation) {}

		//动画结束
		@Override
		public void onAnimationEnd(Animation animation) {
			Intent intent = new Intent(LogoActivity.this, TelmsgActivity.class);
			startActivity(intent);
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		// 初始控件及动画
		img_logo = (ImageView) findViewById(R.id.iv_logo);
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
		animation.setAnimationListener(animationListener);
		// logo图像控件开始动画
		img_logo.startAnimation(animation);
	}
}
