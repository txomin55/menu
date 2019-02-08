//ELEMENTO DE PLATO
Vue.component('dish', {
  data: function(){
    return {  
      nombre : this.plato.nombre,
      descripcion : this.plato.descripcion + " (" + this.plato.kcal + " kcal)",
      idPlato : this.plato.idPlato,
      seleccionado : null
    }
  },
  props: ['plato', 'platoSeleccionado', 'bus'],
  watch: {
    seleccionado : function(newValue){
      this.actualizarSeleccionado(newValue)
    },
    platoSeleccionado: function(newValue){
      this.deseleccionarDiferentes(newValue);
    }
  },
  methods: {
    actualizarSeleccionado: function(value){
      if(value){
        this.$emit('seleccionadoModificado', value);
      }
    },
    deseleccionarDiferentes: function(value){
      if(this.idPlato != value){
        this.seleccionado = null;
      }
    },
    deseleccionarPlato : function(){
        this.seleccionado = null;
    }
  },
  mounted : function(){
    this.bus.$on('submit', this.deseleccionarPlato)
  },
  template: '<vs-list-item :title="nombre" :subtitle="descripcion"><vs-radio v-model="seleccionado" :vs-value="idPlato"></vs-radio></vs-list-item>'
});

//ELEMTNO DE LISTA
Vue.component('list-item', {
  data: function(){
    return {  
      name : this.data.nombre,
      platos : this.data.platos,
      platoSeleccionado : null
    }
  },
  props: ['data', 'bus'],
  methods: {
    desSeleccionarPlatos : function(valor){
      this.platoSeleccionado = valor;
    }
  },
  watch : {
    platoSeleccionado : function(newValue){
       if(newValue){
        this.$emit('platoSeleccionadoModificado', {seccion : this.name, plato : newValue});
      }
    }
  },
  template: '<vs-list><vs-list-header :title="name"></vs-list-header><div v-for="item in platos"><dish v-model="platoSeleccionado" :platoSeleccionado="platoSeleccionado" :plato="item" @seleccionadoModificado="desSeleccionarPlatos" :bus="bus"></dish></div></vs-list>'
});

//ELEMENTO DE LISTA
Vue.component('list-container', {
  data: function () {
    return {
      menu: null,
      identificador: null,
      danger: true,
      platos : [],
      bus : new Vue()
    }
  },
  beforeCreate: function(){
	  
	  fetch('http://localhost:8080/api/menu', {
		    method: 'GET',
		})
		.then((res) => res.json())
		.then((data) =>  {
			this.menu = data;
		});
  },
  watch:{
    identificador: function(newValue){
      if(newValue){
        this.danger = false
      }else{
        this.danger = true
      }
    }
  },
  methods: {
	  anadirPlatosSeleccionados : function(data){
	      this.platos = this.platos.filter(d => {
	        return d.seccion != data.seccion;
	      });
	      this.platos.push(data);
	  },
	
	  enviarEleccion : function(e){
		  fetch('http://localhost:8080/api/menu/eleccion', {
			    method: 'POST',
			    body: JSON.stringify(this.getEleccion()),
			    headers:{
			        'Content-Type': 'application/json'
			      }
			})
			.then(() =>  {
				this.identificador = null;
			      this.$vs.notify({title:'Selecci&oacuten hecha',text:'Tu selecci&oacuten ha sido enviada a cocina', color:"success", position:'top-center'})
			      this.bus.$emit('submit');
			});
	  },
	  
	  getEleccion : function(){
		  const eleccion = {};
		  eleccion.identificador = this.identificador;
		  
		  let descPlatos = "";
		  this.platos.map(v => {
			  return `${v.seccion} : ${v.plato} |`
		  }).forEach(v => {
			  descPlatos = descPlatos + v;
		  });

		  eleccion.platos = descPlatos;
		  return eleccion;
	  }
  },
  template: '<div class="menu-wrapper"><h1>Elija su men&uacute</h1><div style="padding-top:2em;"><vs-input :danger="danger" danger-text="El identificador es obligatorio" placeholder="Identificador" v-model="identificador"/></div><div v-for="item in menu"><list-item @platoSeleccionadoModificado="anadirPlatosSeleccionados" :data="item" :bus="bus"></list-item></div><div style="padding-top:2em;"><vs-button color="primary" :disabled="danger" type="filled" @click="enviarEleccion">Enviar elecci&oacuten</vs-button></div></div>'
});

//APLICACION
new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
});
          
