/** file for common configuration of "data tables", "Bootstrap Dual Listbox - Virtuosoft" shown on various screens **/
datatableCommonConfig = function(){
	
	"use strict";
	
	// Start : common configuration values for data table parameters
	// "iDisplayLength" => default page size
	var iDisplayLength = 50;
	// "aLengthMenu" => page size values and display values
	var aLengthMenu = [
	                   [5, 10, 25, 50, 100, -1],
	                   [5, 10, 25, 50, 100, "All"]
	                  ];
	// End.
	
	// Start :common configuration for non data table parameters but displayed while using data table
	var displyDateFormat = "yyyy-mm-dd HH:MM:ss";
	// End
	
	function getDefaultDisplayLength(){
		  return iDisplayLength;
	}
	
	function getDisplayLengthMenu(){
		  return aLengthMenu;
	}
	
	function getDateInDisplayFormat(lTime){
		return dateFormat(new Date(lTime), displyDateFormat);
	}
	
	return{
		// Share defined common functions for other java scripts files
		getDefaultDisplayLength:getDefaultDisplayLength,
		getDisplayLengthMenu:getDisplayLengthMenu,
		getDateInDisplayFormat:getDateInDisplayFormat
	  }
}();

dualListboxCommonConfig = function(){
	
	"use strict";
	
	// common configuration values for dualListbox
	function getDualListboxCommonConfig(nonSelectedListLabel, selectedListLabel){
		  return {
              nonSelectedListLabel: nonSelectedListLabel,
              selectedListLabel: selectedListLabel,
              preserveSelectionOnMove: 'moved',
              moveOnSelect: false
          };
	}
	
	return{
		// Share defined common functions for other java scripts files
		getDualListboxCommonConfig:getDualListboxCommonConfig
	  }
}();
