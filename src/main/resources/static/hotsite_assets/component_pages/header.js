const header = {
		data: function () {
			return {
			}
		},
		template: `
			<header>
		    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		      <div class="navigation">
		        <div class="container">
		          <div class="navbar-header">
		            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse.collapse">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
								</button>
		            <div class="navbar-brand">
		              <a href="index.html"><h1><span>Projeto</span>Quilombo</h1></a>
		            </div>
		          </div>
		
		          <div class="navbar-collapse collapse">
		            <div class="menu">
		              <ul class="nav nav-tabs" role="tablist">
		              	<li role="presentation"><router-link to="/" v-bind:class="{ active: $root.page_selected=='home' }">Home</router-link></li>
		              	<li role="presentation"><router-link to="/pesquisar">Pesquisar Informações</router-link></li>
		              	<li role="presentation"><router-link to="/page3">Sobre Nós</router-link></li>
		              	<li role="presentation"><router-link to="/contato" v-bind:class="{ active: $root.page_selected=='contato' }">Contato</router-link></li>
		                <li role="presentation"><a href="/sigequi/dashboard">Área Restrita</a></li>
		              </ul>
		            </div>
		          </div>
		        </div>
		      </div>
		    </nav>
		  </header>
		`,
		mounted() {
		}
};