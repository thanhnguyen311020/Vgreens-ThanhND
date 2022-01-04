

async function exportToExcelOrder(fileName, sheetName, report
    , choseDate, myFooter, myData) {

    // getData();
    if (!myData || myData.length === 0) {
        Swal.fire(
            'Lỗi',
            'Dữ liệu không tồn tại',
            'error'
        )
        return;
    }
    console.log('exportToExcel', myData);

    if (report !== '') {
        var myHeader = ['Mã đơn', 'Họ', 'Tên', 'Ngày mua', 'Số điện thoại', 'Trạng Thái', 'Tổng tiền', 'Địa chỉ', 'Ghi chú'];
        exportToExcelPro('Báo cáo đơn hàng', 'Báo cáo đơn hàng', report, myHeader, choseDate, myFooter, [
            { width: 10 },
            { width: 20 },
            { width: 15 },
            { width: 25 },
            { width: 15 },
            { width: 25 },
            { width: 20 },
            { width: 50 },
            { width: 40 },
        ], myData);
        return;
    }

    const wb = new ExcelJS.Workbook();
    const ws = wb.addWorksheet(sheetName);
    const header = Object.keys(myData[0]);
    console.log('header', header);
    ws.addRow(header);
    myData.forEach((rowData) => {
        console.log('rowData', rowData);
        
        row = ws.addRow(Object.values(rowData));
    });

    const buf = await wb.xlsx.writeBuffer();
    saveAs(new Blob([buf]), `${fileName}.xlsx`);
}


async function exportToExcelConsignment(fileName, sheetName, report
  , choseDate, myFooter, myData) {

  // getData();
  if (!myData || myData.length === 0) {
      Swal.fire(
          'Lỗi',
          'Dữ liệu không tồn tại',
          'error'
      )
      return;
  }
  console.log('exportToExcel', myData);

  if (report !== '') {
      var myHeader = ['Mã Lô', 'Tên Lô Hàng',  'Ngày Nhập', 'Hạn sử dụng', 'Số lượng nhập', 'Số lượng hiện tại','Giá gốc', 'Giá bán', 'Nhà cung cấp'];
      exportToExcelPro('Báo cáo lô hàng', 'Báo cáo lô hàng', report, myHeader, choseDate, myFooter, [
          { width: 10 },
          { width: 20 },
          { width: 25 },
          { width: 25 },
          { width: 20 },
          { width: 20 },
          { width: 20 },
          { width: 20 },
          { width: 40 },
      ], myData);
      return;
  }

  const wb = new ExcelJS.Workbook();
  const ws = wb.addWorksheet(sheetName);
  const header = Object.keys(myData[0]);
  console.log('header', header);
  ws.addRow(header);
  myData.forEach((rowData) => {
      console.log('rowData', rowData);
      
      row = ws.addRow(Object.values(rowData));
  });

  const buf = await wb.xlsx.writeBuffer();
  saveAs(new Blob([buf]), `${fileName}.xlsx`);
}


function getStatusExcel(status){
    let statusString = '';
    switch(status) {
        case 0:
            statusString = 'Chưa xác nhận'
          break;
        case 1:
            statusString = 'Đã xác nhận'
          break;
        case 2:
            statusString = 'Hoàn Tất đóng gói'
          break;
        case 3:
            statusString = 'Đang giao hàng'
          break;
        case 4:
            statusString = 'Giao hàng thành công'
          break;
       
        default:statusString = 'Đơn hàng bị hủy'
          // code block
      }

      return statusString;
}
