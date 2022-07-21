const sobre = {
            data: function () {
                return {
                    estado:null,
                    dados:[
                        {
                            uf: "pa",
                            nome:"Pará",                        
                            qtd_quilombos: 20,
                            qtd_comunidades: 30
                        }
                    ]
                }
            },
            template: `     
                <div class="">
                    <div class="row">
                        <div class="col">
                            <h2 class="text-center text-success">Clínica de Direitos Humanos da Amazônia</h2>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 text-justify">
                            <p class="text-muted">A Clínica de Direitos Humanos da Amazônia, 
                            criada em 2011 na perspectiva de integrar ações de pesquisa e extensão 
                            no âmbito do PPGD/ICJ/UFPA, está formalmente estruturada em duas grandes áreas de intervenção:</p>
                            
                            <p class="text-muted">
                                <b>Agroambiental (Direitos Econômicos, Sociais e Culturais):</b> pesquisa e fomento de 
                                políticas públicas relacionadas com ordenamento territorial, gestão e manejo agroflorestal, 
                                regularização fundiária (pequena, média e grande propriedade), reconhecimento de áreas quilombolas 
                                e populações tradicionais, demarcação das áreas indígenas e criação de unidades de conservação, 
                                combate à grilagem, regulamentação e implementação de planos urbanísticos-ambientais, combate ao 
                                trabalho escravo, pesquisa sobre violência no campo e tributação.
                            </p> 
                            
                            <p class="text-muted">
            	               <b>Internacional:</b> capacitação dos discentes para acionar, juntamente com organizações não 
            	               governamentais e movimentos sociais, os Sistemas Internacionais de Proteção, em casos 
            	               paradigmáticos de violações de direitos humanos.
                            </p> 
                            
                            <p class="text-muted">
            	               Suas ações são desenvolvidas pelos docentes, discentes da referida Pós-Graduação e 
            	               discentes da Graduação em Direito e voluntários.
                            </p>
                            
                            <p class="text-muted">
                            O Trabalho foi coordenado pelo Professor<b> Girolamo Domenico Treccani 
                               e por Aianny Naiara Gomes Monteiro.</b>
                            </p>
                            
                            <p class="text-muted">
            	               <b>Criaram o sistema:</b> Anderson Nazareno Alcântara de Oliveira e Isaac Souza Elgrably
                            </p>
                            
                            <p class="text-muted">
            	               <b>Auxiliaram na pesquisa e inserção de dados:</b> 
            	               Iolanda Gentil Solyno; 
            	               Mário Silva; 
            	               Débora Emerique de Lima; 
            	               Samilla Cavalcante Batista; 
            	               Raimundo Fábio Neri Rodrigues; 
            	               Aldo Sena Albernaz Conceição
                            </p>
                               
                        </div>
                    </div>
                </div>
                
            `,
            methods:{
                setaEstado(_estado){
                    this.estado = _estado
                }
            },
            mounted() {
            }
        }