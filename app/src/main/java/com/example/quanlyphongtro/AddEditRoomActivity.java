package com.example.quanlyphongtro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class AddEditRoomActivity extends AppCompatActivity {

    private TextInputEditText etMaPhong, etTenPhong, etGiaThue, etTenNguoiThue, etSoDienThoai;
    private RadioGroup rgTinhTrang;
    private RadioButton rbTrong, rbDaThue;
    private Button btnSave;

    private int roomIndex = -1;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_room);

        initViews();

        if (getIntent().hasExtra("ROOM_INDEX")) {
            roomIndex = getIntent().getIntExtra("ROOM_INDEX", -1);
            isEditMode = true;
            setTitle("Sửa phòng trọ");
            loadRoomData();
        } else {
            setTitle("Thêm phòng trọ");
        }

        btnSave.setOnClickListener(v -> saveRoom());
    }

    private void initViews() {
        etMaPhong = findViewById(R.id.et_ma_phong);
        etTenPhong = findViewById(R.id.et_ten_phong);
        etGiaThue = findViewById(R.id.et_gia_thue);
        etTenNguoiThue = findViewById(R.id.et_ten_nguoi_thue);
        etSoDienThoai = findViewById(R.id.et_so_dien_thoai);
        rgTinhTrang = findViewById(R.id.rg_tinh_trang);
        rbTrong = findViewById(R.id.rb_trong);
        rbDaThue = findViewById(R.id.rb_da_thue);
        btnSave = findViewById(R.id.btn_save);

        // Default selection
        rbTrong.setChecked(true);
    }

    private void loadRoomData() {
        Room room = RoomDataCenter.getInstance().getRoomList().get(roomIndex);
        etMaPhong.setText(room.getMaPhong());
        etTenPhong.setText(room.getTenPhong());
        etGiaThue.setText(String.valueOf(room.getGiaThue()));
        etTenNguoiThue.setText(room.getTenNguoiThue());
        etSoDienThoai.setText(room.getSoDienThoai());

        if ("Còn trống".equals(room.getTinhTrang())) {
            rbTrong.setChecked(true);
        } else {
            rbDaThue.setChecked(true);
        }
    }

    private void saveRoom() {
        String maPhong = etMaPhong.getText().toString().trim();
        String tenPhong = etTenPhong.getText().toString().trim();
        String giaThueStr = etGiaThue.getText().toString().trim();
        String tenNguoiThue = etTenNguoiThue.getText().toString().trim();
        String soDienThoai = etSoDienThoai.getText().toString().trim();
        String tinhTrang = rbTrong.isChecked() ? "Còn trống" : "Đã thuê";

        if (maPhong.isEmpty() || tenPhong.isEmpty() || giaThueStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin bắt buộc", Toast.LENGTH_SHORT).show();
            return;
        }

        double giaThue = Double.parseDouble(giaThueStr);
        Room room = new Room(maPhong, tenPhong, giaThue, tinhTrang, tenNguoiThue, soDienThoai);

        if (isEditMode) {
            RoomDataCenter.getInstance().updateRoom(roomIndex, room);
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        } else {
            RoomDataCenter.getInstance().addRoom(room);
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
