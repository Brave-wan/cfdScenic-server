/**
 * 参数为子表数据
 * 返回值为子表数据的备份数组
 * 用法示例：var sonData=getSonData(row.list);
 * 			$('#sonGridId').datagrid('loadData',{total:sonData.length,rows:sonData});
 * @param data
 * @returns {Array}
 */
function getSonData(data)
{
	var returnData=[];
	if(data!=null&&data!=[]&&data!=undefined)
		for(index in data)
		{
			returnData.push(data[index]);
		}
	return returnData;
}