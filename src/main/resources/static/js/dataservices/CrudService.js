var crudService = {
  findAll(URL, page, size, sort, fn) {
	  if(page<0){
		  page=0
	  }
	  if(size<=0){
		  size=5
	  }
	  if(sort==null){
		  sort="id"
	  }	  
	  
	  axios
	      .get(URL+"?page="+page+"&size="+size+"&sort="+sort)
	      .then(response => fn(response))
	      .catch(error => fn(error.response))
  },
  findAllNoPagination(URL, fn) {	  
	  axios
	      .get(URL)
	      .then(response => fn(response))
	      .catch(error => fn(error.response))
  },
  findAllNoPagination2(URL, object, fn) {    
      axios
          .get(URL, object, {headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}})
          .then(response => fn(response))
          .catch(error => fn(error.response))
  },

  findById(URL, id, fn) {
    axios
      .get(URL + id)
      .then(response => fn(response))
      .catch(error => fn(error.response))
  },

  create(URL, object, fn) {
    axios
      .post(URL, object, {headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}})
      .then(response => fn(response))
      .catch(error => fn(error.response))
  },
  
  anyPostOperationWihtObject(URL, object, fn) {
    axios
      .post(URL, object, {headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}})
      .then(response => fn(response))
      .catch(error => fn(error.response))
  },
	  
  update(URL, id, object, fn) {
    axios
      .put(URL + id, object, {headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}})
      .then(response => fn(response))
      .catch(error => fn(error.response))
  },

  delete(URL, id, fn) {
    axios
      .delete(URL + id)
      .then(response => fn(response))
      .catch(error => fn(error.response))
  }
}
