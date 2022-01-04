

async function exportToExcel(fileName, sheetName, report
    ,choseDate,myFooter,myData) {

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
		var myHeader = ['Mã đơn','Họ', 'Tên','Ngày mua', 'Sản phẩm', 'Số Lượng', 'Giá Tiền','Thành Tiền'];
		exportToExcelPro('Users', 'Users', report, myHeader,choseDate, myFooter, [
			{ width: 10 },
			{ width: 15 },
			{ width: 15 },
			{ width: 20 },
			{ width: 40 },
			{ width: 10 },
			{ width: 20 },
			{ width: 20 },
		],myData);
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

async function exportToExcelPro(
	fileName,
	sheetName,
	report,
	myHeader,
	choseDate,
	myFooter,
	widths,
    myData
) {
	if (!myData || myData.length === 0) {
		console.error('Chưa có data');
		return;
	}
	console.log('exportToExcel', myData);

	const wb = new ExcelJS.Workbook();
	const ws = wb.addWorksheet(sheetName);
	const columns = myHeader?.length;
	const title = {
		border: true,
		money: false,
		height: 40,
		font: { size: 18, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: '00B050',
			},
		},
	};
    const dateChosse = {
		border: true,
		money: false,
		height: 25,
		font: { size: 12, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: 'FFFFFF',
			},
		},
	};
	const header = {
		border: true,
		money: false,
		height: 30,
		font: { size: 12, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: 'BFBFBF',
			},
		},
	};
	const data = {
		border: true,
		money: true,
		height: 0,
		font: { size: 12, bold: false, color: { argb: '000000' } },
		alignment: null,
		fill: null,
	};
	const footer = {
		border: true,
		money: true,
		height: 30,
		font: { size: 15, bold: true, color: { argb: '0D0D0D' } },
		alignment: null,
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: '00B050',
			},
		},
	};
	if (widths && widths.length > 0) {
		ws.columns = widths;
	}

	let row = addRow(ws, [report], title);
	mergeCells(ws, row, 1, columns);

	console.log("chooseDate:", choseDate)
	 row = addRow(ws, [choseDate], dateChosse);
	mergeCells(ws, row, 1, columns);

	addRow(ws, myHeader, header);
	// console.log('wb', wb);
	myData.forEach((row) => {
		addRow(ws, Object.values(row), data);
	});
	// console.log('myFooter', myFooter);

	row = addRow(ws, myFooter, footer);
	mergeCells(ws, row, 1, columns - 1);

	const buf = await wb.xlsx.writeBuffer();
	saveAs(new Blob([buf]), `${fileName}.xlsx`);
}

function addRow(ws, data, section) {
	const borderStyles = {
		top: { style: 'thin' },
		left: { style: 'thin' },
		bottom: { style: 'thin' },
		right: { style: 'thin' },
	};
	const row = ws.addRow(data);
	console.log('addRow', section, data);
	row.eachCell({ includeEmpty: true }, (cell, colNumber) => {
		if (section?.border) {
			cell.border = borderStyles;
		}
		if (section?.money && typeof cell.value === 'number') {
			cell.alignment = { horizontal: 'right', vertical: 'middle' };
			cell.font = { size: 12, bold: true, color: { argb: '000000' } };
			cell.numFmt = ' #,### ;[Red]- #,###';
		}
		if (section?.alignment) {
			cell.alignment = section.alignment;
		} else {
			cell.alignment = { vertical: 'middle' };
		}
		if (section?.font) {
			cell.font = section.font;
		}
		if (section?.fill) {
			cell.fill = section.fill;
		}
	});
	if (section?.height > 0) {
		row.height = section.height;
	}
	return row;
}

function mergeCells(ws, row, from, to) {
	// console.log(
	// 	'mergeCells',
	// 	row,
	// 	from,
	// 	to,
	// 	row.getCell(from)._address,
	// 	row.getCell(to)._address
	// );
	ws.mergeCells(`${row.getCell(from)._address}:${row.getCell(to)._address}`);
}

function columnToLetter(column) {
	var temp,
		letter = '';
	while (column > 0) {
		temp = (column - 1) % 26;
		letter = String.fromCharCode(temp + 65) + letter;
		column = (column - temp - 1) / 26;
	}
	return letter;
}



// Doanh thu theo tháng
async function exportToExcelTurnover(fileName, sheetName, report
    ,choseDate,myFooter,myData) {

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
		var myHeader = ['Tháng', 'Số lượng đơn hàng', 'Giảm giá', 'Doanh thu'];
		exportToExcelProTurnover('Báo cáo doanh số', 'báo cáo Doanh Số', report, myHeader,choseDate, myFooter, [
			{ width: 20 },
			{ width: 25 },
			{ width: 20 },
			{ width: 20 },
	
		],myData);
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

async function exportToExcelProTurnover(
	fileName,
	sheetName,
	report,
	myHeader,
	choseDate,
	myFooter,
	widths,
    myData
) {
	if (!myData || myData.length === 0) {
		console.error('Chưa có data');
		return;
	}
	console.log('exportToExcel', myData);

	const wb = new ExcelJS.Workbook();
	const ws = wb.addWorksheet(sheetName);
	const columns = myHeader?.length;
	const title = {
		border: true,
		money: false,
		height: 40,
		font: { size: 18, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: '00B050',
			},
		},
	};
    const dateChosse = {
		border: true,
		money: false,
		height: 25,
		font: { size: 12, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: 'FFFFFF',
			},
		},
	};
	const header = {
		border: true,
		money: false,
		height: 30,
		font: { size: 12, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: 'BFBFBF',
			},
		},
	};
	const data = {
		border: true,
		money: true,
		height: 0,
		font: { size: 12, bold: false, color: { argb: '000000' } },
		alignment: null,
		fill: null,
	};
	const footer = {
		border: true,
		money: true,
		height: 30,
		font: { size: 15, bold: true, color: { argb: '0D0D0D' } },
		alignment: null,
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: '00B050',
			},
		},
	};
	if (widths && widths.length > 0) {
		ws.columns = widths;
	}

	let row = addRow(ws, [report], title);
	mergeCells(ws, row, 1, columns);

	console.log("chooseDate:", choseDate)
	 row = addRow(ws, [choseDate], dateChosse);
	mergeCells(ws, row, 1, columns);

	addRow(ws, myHeader, header);
	// console.log('wb', wb);
	myData.forEach((row) => {
		addRow(ws, Object.values(row), data);
	});
	// console.log('myFooter', myFooter);

	row = addRow(ws, myFooter, footer);
	mergeCells(ws, row, 1, columns - 1);

	const buf = await wb.xlsx.writeBuffer();
	saveAs(new Blob([buf]), `${fileName}.xlsx`);
}



// Doanh thu sản phẩm theo thời gian
async function exportToExcelTurnoverProduct(fileName, sheetName, report
    ,choseDate,myFooter,myData) {

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
		var myHeader = ['Mã Sản Phẩm', 'Tên Sản Phẩm', 'Lượt mua', 'Tổng Doanh Thu'];
		exportToExcelProTurnoverProductPro('Báo cáo doanh tu sản phẩm', 'Báo cáo doanh tu sản phẩm', report, myHeader,choseDate, myFooter, [
			{ width: 20 },
			{ width: 25 },
			{ width: 20 },
			{ width: 20 },
	
		],myData);
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

async function exportToExcelProTurnoverProductPro(
	fileName,
	sheetName,
	report,
	myHeader,
	choseDate,
	myFooter,
	widths,
    myData
) {
	if (!myData || myData.length === 0) {
		console.error('Chưa có data');
		return;
	}
	console.log('exportToExcel', myData);

	const wb = new ExcelJS.Workbook();
	const ws = wb.addWorksheet(sheetName);
	const columns = myHeader?.length;
	const title = {
		border: true,
		money: false,
		height: 40,
		font: { size: 18, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: '00B050',
			},
		},
	};
    const dateChosse = {
		border: true,
		money: false,
		height: 25,
		font: { size: 12, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: 'FFFFFF',
			},
		},
	};
	const header = {
		border: true,
		money: false,
		height: 30,
		font: { size: 12, bold: false, color: { argb: '0D0D0D' } },
		alignment: { horizontal: 'center', vertical: 'middle' },
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: 'BFBFBF',
			},
		},
	};
	const data = {
		border: true,
		money: true,
		height: 0,
		font: { size: 12, bold: false, color: { argb: '000000' } },
		alignment: null,
		fill: null,
	};
	const footer = {
		border: true,
		money: true,
		height: 30,
		font: { size: 15, bold: true, color: { argb: '0D0D0D' } },
		alignment: null,
		fill: {
			type: 'pattern',
			pattern: 'solid', //darkVertical
			fgColor: {
				argb: '00B050',
			},
		},
	};
	if (widths && widths.length > 0) {
		ws.columns = widths;
	}

	let row = addRow(ws, [report], title);
	mergeCells(ws, row, 1, columns);

	console.log("chooseDate:", choseDate)
	 row = addRow(ws, [choseDate], dateChosse);
	mergeCells(ws, row, 1, columns);

	addRow(ws, myHeader, header);
	// console.log('wb', wb);
	myData.forEach((row) => {
		addRow(ws, Object.values(row), data);
	});
	// console.log('myFooter', myFooter);

	row = addRow(ws, myFooter, footer);
	mergeCells(ws, row, 1, columns - 1);

	const buf = await wb.xlsx.writeBuffer();
	saveAs(new Blob([buf]), `${fileName}.xlsx`);
}




