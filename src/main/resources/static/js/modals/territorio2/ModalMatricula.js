const matriculaURL = URL_BASE+"/territorios/matricula";

const ModalMatricula = {
            props: ['data','territorio'],
            data:function(){
                return{
                    object:{
                        id: null,
                        cartorioRegistroImoveis: null,
                        livroRegistroImoveis: null,
                        folhaRegistroImoveis: null,
                        matricula: null
                        }
                    ,
                    mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
                    diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
                    isMatriculaSetada: false
                }
            },
            methods:{
                getMatricula(){
                    crudService.findById(URL_BASE+"/territorios/", this.territorio.id, r => {
                        if(r.data.matricula != null){
                        	this.isMatriculaSetada = true
                            this.object = r.data.matricula    
                        }
                    })
                },
                save(setado) {
                    if(setado){
                        this.object.territorio = this.territorio.id
                        crudService.update(matriculaURL, "", this.object, r => {
                            console.log(r.data)
                            if(r.status == 200){
                                this.$buefy.toast.open({
                                    duration: 3000,
                                    message: r.data.message,
                                    position: 'is-top',
                                    type: 'is-success'
                                })
                            }else{
                                this.$buefy.dialog.alert({
				                    title: 'Erro',
				                    message: r.data.message,
				                    type: 'is-danger',
				                    hasIcon: true,
				                    icon: 'times-circle',
				                    iconPack: 'fa'
				                })
                            }
                        })
                    }else{
                        this.object.territorio = this.territorio.id
                        crudService.create(matriculaURL, this.object, r => {
                            console.log(r.data)
                            if(r.status == 200){
                                this.$buefy.toast.open({
                                    duration: 3000,
                                    message: r.data.message,
                                    position: 'is-top',
                                    type: 'is-success'
                                })
                            }else{
                                this.$buefy.dialog.alert({
				                    title: 'Erro',
				                    message: r.data.message,
				                    type: 'is-danger',
				                    hasIcon: true,
				                    icon: 'times-circle',
				                    iconPack: 'fa'
				                })
                            }
                        })
                    }
                    
                    this.$parent.close() 
                },
            },
            watch:{
                object:function(val){
                    this.object = val;
                }
            },
            created() {
                this.getMatricula()
                
                 
            },
            template: `
                    <div class="modal-card" >
                        <header class="modal-card-head">
                            <p class="modal-card-title">
                                <i class="icon-interface-windows" style="font-size: 30px;"></i> Matrícula
                            </p>
                        </header>
                        <section class="modal-card-body"> 
                            <template>
                                <b-field  label="Cartório de Registro de Imóveis" >
                                    <b-input v-model="object.cartorioRegistroImoveis"   placeholder="Cartório de Registro de Imóveis" type="text" ></b-input>
                                </b-field>
                                
                                 <b-field  label="Livro de Registro de Imóveis" >
                                    <b-input v-model="object.livroRegistroImoveis"   placeholder="Livro de Registro de Imóveis" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Folha de Registro de Imóveis" >
                                    <b-input v-model="object.folhaRegistroImoveis"   placeholder="Folha de Registro de Imóveis" type="text" ></b-input>
                                </b-field>
                                
                                 <b-field  label="Matrícula de Registro de Imóveis" >
                                    <b-input v-model="object.matricula"   placeholder="Matrícula de Registro de Imóveis" type="text" ></b-input>
                                </b-field>
                                
                                 
                            </template>
                        </section>
                        <footer class="modal-card-foot">
                            <b-button v-show="!isMatriculaSetada" @click.native="save(isMatriculaSetada)"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
                            <b-button v-show="isMatriculaSetada" @click.native="save(isMatriculaSetada)"  type="is-info"><i class="fa fa-edit"></i> Editar</b-button>
                            <b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
                        </footer>
                    </div>
            `
    }