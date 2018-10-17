package cc.wco.permissiondemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cc.wco.permission.PermissionActivity;
import cc.wco.permission.PermissionChecker;

public class MainActivity extends AppCompatActivity {

    //权限申请
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE_PERMISSION = 0; //权限请求码
    private PermissionChecker mPermissionsChecker; //权限检测器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化权限检测器
        mPermissionsChecker = new PermissionChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //检查是否缺少权限，如缺少则进入授权页面
        //调用静态方法启动授权页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionActivity.startActivityForResult(this, REQUEST_CODE_PERMISSION, PERMISSIONS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //返回权限申请结果
        if (requestCode == REQUEST_CODE_PERMISSION) {
            //拒绝时：关闭页面，缺少权限法运行
            if (resultCode == PermissionActivity.PERMISSIONS_DENIED) {
                finish();
            }
        }
    }
}
