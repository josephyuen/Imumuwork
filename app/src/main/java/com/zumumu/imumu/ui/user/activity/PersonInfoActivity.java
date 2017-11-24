package com.zumumu.imumu.ui.user.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nzf.mvpframe.base.BaseActivity;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.user.contract.PersonInfoContract;
import com.zumumu.imumu.ui.user.model.PInfoModel;
import com.zumumu.imumu.ui.user.presenter.PInfoPresenter;
import com.zumumu.imumu.ui.view.ClearEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2017/1/10.
 * 个人中心的用户个人资料界面
 */

public class PersonInfoActivity extends BaseActivity<PInfoPresenter, PInfoModel> implements PersonInfoContract.PInfoSuperView {


    @Bind(R.id.tv_pinfo_edit)
    TextView tvPinfoEdit;
    @Bind(R.id.sdv_pinfo_headpic)
    SimpleDraweeView sdvPinfoHeadpic;
    @Bind(R.id.et_pinfo_nickname)
    ClearEditText etPinfoNickname;
    @Bind(R.id.et_pinfo_gender)
    ClearEditText etPinfoGender;
    @Bind(R.id.et_pinfo_email)
    ClearEditText etPinfoEmail;
    private boolean isEditing = false;
    private View mView;
    private static final int PHOTO_PICKED_WITH_DATA = 1881;
    private static final int CAMERA_WITH_DATA = 1882;
    private static final int PHOTO_CROP_RESULT = 1884;
    private static final int CAMERA_CROP_RESULT = 1883;
    private PopupWindow mSetPhotoPop; //选择头像弹窗
    private File mCurrentPhotoFile;//相机拍照后的图片文件
    private static final int ICON_SIZE = 96;
    private Bitmap imageBitmap;


    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        isEdit(false, etPinfoEmail);
        isEdit(false, etPinfoGender);
        isEdit(false, etPinfoNickname);

    }

    @Override
    public void returnPInfoChannels(String pInfoMsg) {

    }

    @Override
    public void returnPInfoChannelsError(String msg) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = View.inflate(this,R.layout.activity_personal_info,null);
        ButterKnife.bind(this);
    }


    /**
     * 设置输入框是否可以编辑
     *
     * @param value
     * @param editText
     */
    private void isEdit(boolean value, EditText editText) {

        if (value) {

            editText.setFocusable(true);

            editText.setFocusableInTouchMode(true);

            editText.setFilters(new InputFilter[]{new InputFilter() {

                @Override

                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                    return null;

                }

            }});

        } else {
            //设置不可获取焦点

            editText.setFocusable(false);

            editText.setFocusableInTouchMode(false);

            //输入框无法输入新的内容

            editText.setFilters(new InputFilter[]{new InputFilter() {

                @Override

                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                    return source.length() < 1 ? dest.subSequence(dstart, dend) : "";
                }

            }});
        }
    }


    @OnClick({R.id.tv_pinfo_edit, R.id.sdv_pinfo_headpic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pinfo_edit:
                isEditing = !isEditing;
                if (isEditing) {
                    isEdit(true, etPinfoNickname);
                    isEdit(true, etPinfoGender);
                    isEdit(true, etPinfoEmail);
                    etPinfoNickname.requestFocus();
                    tvPinfoEdit.setText("完成");
                } else {
                    isEdit(false, etPinfoEmail);
                    isEdit(false, etPinfoGender);
                    isEdit(false, etPinfoNickname);
                    tvPinfoEdit.setText("编辑");
                }
                break;
            case R.id.sdv_pinfo_headpic:
                showSelectPicPop();

                break;
        }
    }

    /**
     * 弹出popwindow,询问用户从图库还是拍照选择图片
     */
    private void showSelectPicPop() {
        View mainView = LayoutInflater.from(this).inflate(R.layout.select_photo_alert, null);
        Button btnTakePhoto = (Button) mainView.findViewById(R.id.btn_take_photo);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetPhotoPop.dismiss();
                // 拍照获取
                doTakePhoto();
            }
        });
        Button btnCheckFromGallery = (Button) mainView.findViewById(R.id.btn_check_from_gallery);
        btnCheckFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetPhotoPop.dismiss();
                // 相册获取
                doPickPhotoFromGallery();
            }
        });
        Button btnCancle = (Button) mainView.findViewById(R.id.btn_cancel);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetPhotoPop.dismiss();
            }
        });

        mSetPhotoPop = new PopupWindow(this);
        mSetPhotoPop = setParamOfPop(mSetPhotoPop, mainView);
        mSetPhotoPop.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        mSetPhotoPop.update();



    }

    /**
     * 通过拍照获取图片
     */
    private void doTakePhoto() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)) {
            Toast.makeText(this, "SD卡没有挂载,请检查SD卡状态", Toast.LENGTH_SHORT).show();
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/Photo");
        if (!file.exists()) {
            file.mkdirs();
        }
        mCurrentPhotoFile = new File(file, "touxiang.jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
        startActivityForResult(intent, CAMERA_WITH_DATA);
    }

    /**
     * 从图库获取图片
     */
    private void doPickPhotoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
    }

    /**
     * 标准popwindow参数设置
     *
     * @param pop
     * @param view1
     * @return
     */
    public PopupWindow setParamOfPop(PopupWindow pop, View view1) {
        pop.setFocusable(true);
        pop.setTouchable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view1);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setAnimationStyle(R.style.bottomStyle);
        return pop;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            switch (requestCode) {
                case PHOTO_PICKED_WITH_DATA:
                    // 相册选择图片后裁剪图片
                    startPhotoZoom(data.getData());
                    break;
                case PHOTO_CROP_RESULT:
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        imageBitmap = extras.getParcelable("data");
                        sdvPinfoHeadpic.setImageBitmap(imageBitmap);

                    }
                    break;
                case CAMERA_WITH_DATA:
                    // 相机拍照后裁剪图片
                    doCropPhoto(mCurrentPhotoFile);
                    break;
                case CAMERA_CROP_RESULT:
                    imageBitmap = data.getParcelableExtra("data");
                    sdvPinfoHeadpic.setImageBitmap(imageBitmap);
                    break;
            }
        }
    }

    /**
     * 相机拍照后裁剪图片
     *
     * @param f
     */
    private void doCropPhoto(File f) {
        MediaScannerConnection.scanFile(this, new String[]{f.getAbsolutePath()}, new String[]{null}, null);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(f), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", ICON_SIZE);
        intent.putExtra("outputY", ICON_SIZE);
        intent.putExtra("return-data", true);
        this.startActivityForResult(intent, CAMERA_CROP_RESULT);
    }

    /**
     * 相册选择图片后裁剪图片
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", ICON_SIZE);
        intent.putExtra("outputY", ICON_SIZE);
        intent.putExtra("return-data", true);
        this.startActivityForResult(intent, PHOTO_CROP_RESULT);
    }

}
