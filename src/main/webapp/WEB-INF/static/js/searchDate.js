function selectDate(combox,startDate,endDate) {

	 	$('#'+combox).combobox({
			onChange:function(){
				var vselect = $('#'+combox).combobox('getValue');
				switch(vselect)
				{
					case 'all':subtractDateDay()
						$('#'+startDate).datebox('setValue','');
						$('#'+endDate).datebox('setValue','');
					  break;
					case 'day':subtractDateDay()
						$('#'+startDate).datebox('setValue',subtractDateDay(1));
						$('#'+endDate).datebox('setValue',new Date().format("yyyy-MM-dd"));
					  break;
					case 'week':
					  $('#'+startDate).datebox('setValue',subtractDateDay(7));
					  $('#'+endDate).datebox('setValue',new Date().format("yyyy-MM-dd"));	
					  break;
					case 'month':
					  $('#'+startDate).datebox('setValue',subtractDateDay(30));
					  $('#'+endDate).datebox('setValue',new Date().format("yyyy-MM-dd"));		
					  break;
					case 'quarter':
					  $('#'+startDate).datebox('setValue',subtractDateDay(90));
					  $('#'+endDate).datebox('setValue',new Date().format("yyyy-MM-dd"));	
					  break;
					default:
					  
				}
				
			}
			
		});		
	}