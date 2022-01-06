const contato = {
  data: function () {
    return {
      title: "Página terceira",
      form: {
        nome: "",
        email: "",
        assunto: "",
        mensagem: "",
      },
      sucesso: false,
      erro: false,
      erroMessage: "",
    };
  },
  template: `
			    <div class="">
			      
			      <div class="">
			          <div class="row pt-4">
    			      	  <div class="col">
    			              <h3 class="text-center text-success">Deixe sua mensagem!</h3>
    			          </div>
					  </div>
					  
					  <div class="row pt-4">
    			      	  <div class="col text-center font-weight-bold">
							<p>Cidade Universitária Prof. José da Silveira Netto. Campus Profissional, Bloco L - altos.</p>
							<p>Rua Augusto Corrêa, nº 01, Guamá, Belém, PA, Brasil. CEP:66075-900.</p>
    			          </div>
					  </div>
					  
			          
			          <div class="row pt-4">
    			      	  <div class="col-sm-12 col-xs-12 offset-md-2 col-md-8 offset-lg-2 col-lg-8">
			                  <b-alert variant="success" :show="sucesso" dismissible>Sua mensagem foi enviada com sucesso. Responderemos em breve.</b-alert>

                              <b-alert  variant="danger" :show="erro" dismissible>
                                  {{erroMessage}}
                              </b-alert>
    			          </div>
			          </div>

		            <div class="row justify-content-start  pt-4">
			          <div class="col-sm-12 col-xs-12 offset-md-2 col-md-8 offset-lg-2 col-lg-8">
    			          
    			            <div class="form-group">
    			              <input v-model="form.nome" type="text" name="name" class="form-control" id="name" placeholder="Seu nome" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
    			              <div class="validation"></div>
    			            </div>
    			            <div class="form-group">
    			              <input v-model="form.email" type="email" class="form-control" name="email" id="email" placeholder="Seu e-mail" data-rule="email" data-msg="Please enter a valid email" />
    			              <div class="validation"></div>
    			            </div>
    			            <div class="form-group">
    			              <input v-model="form.assunto" type="text" class="form-control" name="subject" id="subject" placeholder="Assunto" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
    			              <div class="validation"></div>
    			            </div>
    			            <div class="form-group">
    			              <textarea v-model="form.mensagem" class="form-control" name="message" rows="5" data-rule="required" data-msg="Please write something for us" placeholder="Sua mensagem"></textarea>
    			              <div class="validation"></div>
    			            </div>
    			            <div class="text-center">
    			                <button @click="save" type="submit" name="submit" class="btn btn-primary btn-md" required="required">Enviar</button>
    			            </div>
    			          
			            </div>
			        </div>
			    </div>
		`,
  methods: {
    save() {
      if (
        this.form.nome == "" ||
        this.form.email == "" ||
        this.form.assunto == "" ||
        this.form.mensagem == ""
      ) {
        this.erro = true;
        this.sucesso = false;
        this.erroMessage =
          "Existem campos em branco. Verifique e tente novamente!";
      } else {
        axios
          .post("/sigequi/api/v2/contatos", this.form, {
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
          })
          .then((response) => {
            this.sucesso = true;
            this.erro = false;
            this.form.nome = "";
            this.form.email = "";
            this.form.assunto = "";
            this.form.mensagem = "";
          })
          .catch((error) => {
            console.log(error.response);
            this.erro = true;
            this.sucesso = false;
            this.erroMessage =
              "Erro[" +
              error.response.data.status +
              "]: " +
              error.response.data.error +
              "\nMensagem: " +
              error.response.data.message;
          });
      }
    },
  },
  mounted() {
    this.$root.page_selected = "contato";
  },
};
