var express 	= require('express');
var app 		= express();

var pug 		= require('pug');
var router 		= express.Router();

app.set('view engine','pug');

app.use(express.static(__dirname + '/public'));

router.use(function(req, res, next) {
    console.log('Solicitan una petición');
    next(); 
});


router.route('/')
	.get(function(req, res){
		res.render('index')
	});

router.route('/categorias')
	.get(function(req, res){
		res.render('categorias')
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


