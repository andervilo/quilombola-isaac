const URL = "/api/v1/documentos/";
var documentoService = {
  findAll(fn) {
    axios
      .get(URL)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get(URL + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(documento, fn) {
    axios
      .post(URL, documento)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, documento, fn) {
    axios
      .put(URL + id, documento)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  delete(id, fn) {
    axios
      .delete(URL + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}
