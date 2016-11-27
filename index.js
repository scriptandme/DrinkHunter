var express 	= require('express');
var app 		= express();

var pug 		= require('pug');
var router 		= express.Router();

app.set('view engine','pug');

app.use(express.static(__dirname + '/public'));

var Local = require('./models/local').Local
var Precio = require('./models/precio').Precio
var Categoria = require('./models/categoria').Categoria
var Producto = require('./models/producto').Producto


router.use(function(req, res, next) {
    console.log('Solicitan una petición');
    next(); 
});


router.route('/')
	.get(function(req, res){
		Producto.findAll({ offset: 20, limit: 20 }).then(function(producto){
		
			res.render('index',{
				'productos': producto,
				'tituloh3' : 'Ofertas'
			});
		});
	});

router.route('/categorias')
	.get(function(req, res){
		Categoria.findAll({}).then(function(data){
			res.render('categorias', {
				'categorias': data
			});
		});
	});
router.route('/categoria/:id/:nombre')
	.get(function(req, res){
		Producto.findAll({where:{idCategoria: req.params.id}}).then(function(producto){
			res.render('index',{
				'productos': producto,
				'tituloh3' : req.params.nombre
			});
		});
	});
router.route('/mapa-locales')
	.get(function(req,res){
		res.render('botillerias')
	});
router.route('/preferencias')
	.get(function(req,res){
		res.render('preferencias')
	});
router.route('/sobre-drink-hunter')
	.get(function(req,res){
		res.render('sobre-drink-hunter')
	})


app.use('/drink-hunter',router);

app.listen(3001, function() {  
  console.log("Node server running on http://localhost:3000");
});


