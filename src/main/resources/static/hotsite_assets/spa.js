const home = {
    data: function() {
        return {
            title: 'Home page'
        }
    },
    template: '<div ><mapa></mapa></div>',
    mounted() {
        this.$root.page_selected = "home";
    }
};

const pesquisar = {
    data: function () {
        return {
            title: 'Página 2'
        }
    },
    template: '<div >Segunda página</div>',
    mounted() {
        this.$root.title = this.title;
    }
};

const page3 = {
    data: function () {
        return {
            title: 'Página terceira'
        }
    },
    template: '<div >Terceira página</div>',
    mounted() {
        this.$root.title = this.title;
    }
};



const errorNotFound = {
    data: function () {
        return {
            title: 'Not Found'
        }
    },
    template: '<div >Erro 404</div>',
    mounted() {
        this.$root.title = this.title;
    }
};

const routes = [
    { path: '/', component: home },
    { path: '/pesquisar', component: pesquisar },
    { path: '/page3', component: page3 },
    { path: '/contato', component: contato },
    { path: '/*', component: errorNotFound },
];

const router = new VueRouter({
    routes,
//    mode: 'history',
    base: '/sigequi/'
});

Vue.component('page-footer', footer)
Vue.component('page-header', header)
Vue.component('mapa', mapa)

new Vue({
    el: '#app',
    template: `
    <div>
        <page-header></page-header>
		  
    <main class="container" style="margin-top:90px;">
        	<router-view></router-view>
        </main>
        
        <page-footer></page-footer>
        
    </div>
    
    
    `,
    data: function() {
        return {
            title: '...',
            page_selected: "home"
        }
    },
    router
});
