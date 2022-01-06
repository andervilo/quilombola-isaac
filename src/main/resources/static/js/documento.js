//httpVueLoader.register(Vue, '../components/datatable/DataTable');
httpVueLoader.register(Vue, '/components/calendar/Calendar.vue');
	
// 	Vue.component('DataTable', DataTable);
	Vue.component('Calendar', Calendar);
    	var app = new Vue({
        	el: '#contents',
			data:{
				msg:"VueJS funcionando!",
				tableData:[],
				selectedCity: null,
				cities: [
					{name: 'London', code: 'LND'},
					{name: 'Paris', code: 'PRS'},
					{name: 'Rome', code: 'RM'}
				]
			},
			mounted() {
            	documentoService.findAll(r => {
					this.tableData = r.data
					console.log(this.tableData)
				})
          	},
    	});