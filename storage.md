#Đọc ghi file internal
--> Truy xuất file thông qua phương thức openFileInputStream() hoặc openFileOutStream --> đọc ghi file nhị phân.
--> Đọc ghi file unicode --> FileReader --> có được đường dẫn file --> getFileDir (lấy được đường dẫn thư mục chứa file cần đọc)
--> Đường dẫn lưu trữ file internal: /data/data/<package_name>/files/

#Đọc ghi file external
- Bước 1: lấy đường dẫn root external --> Environment.getExternalStorageDirectory().getPath()
- Bước 2: Xin quyền đọc ghi external --> android manifest:
  + <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
  + <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  
  hoặc
  
  + <permission-group android:name="android.permission-group.STORAGE" />

# Demo file explorer
Liệt kê danh sách file, thư mục của external
- Bước 1: Lấy đường root
- Bước 2: Xin Quyền
- Bước 3: Lấy danh sách file, thư mục trong root
- Bước 4: Bắt sự kiện onItemClickListener
- Bước 5: Liệt kê ds file, thư mục lên listView
- Bước 6: xử lý sự kiện
  + Nếu currentItem là thư mục
    + Quay lại bước 5
  + Ngược lại --> file --> xử lý file
  + Ngược lại --> back --> Quay lại bước 5
