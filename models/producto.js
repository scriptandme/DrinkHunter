var Sequelize = require('sequelize');

const   localhost = "13.93.225.126",
		database = "copetes_bd",
		usernamedb = "root",
		passworddb = "wololo11";

var sequelize = new Sequelize(database, usernamedb, passworddb, {
	host: localhost,
  	dialect: 'mysql',
  	pool: {
  		min:1,
		max:2,
		idle:1000
  	}
});
var Categoria = require('./categoria').Categoria

var Producto = sequelize.define('producto', {
	idProducto: {
		type: Sequelize.INTEGER,
		field: 'ID_PRODUCTO', // Will result in an attribute that is firstName when user facing but first_name in the database
		primaryKey: true,
		autoIncrement: true
	},
	nombre: {
		type: Sequelize.STRING,
		field: 'NOMBRE'
	},
	volumen: {
		type: Sequelize.STRING,
		field: 'VOLUMEN'
	},
	ultimoPrecio: {
		type: Sequelize.STRING,
		field: 'ULTIMO_PRECIO'
	},
	link: {
		type: Sequelize.STRING,
		field: 'LINK'
	},
	imagen: {
		type: Sequelize.STRING,
		field: 'imagen'
	},
	idCategoria: {
		type: Sequelize.INTEGER,
		field: 'ID_CATEGORIA'
	},
	rate: {
		type: Sequelize.INTEGER,
		field: 'RATE'
	},
	creado: {
		type: Sequelize.DATE,
		field: 'CREADO'
	},
	actualizado: {
		type: Sequelize.DATE,
		field: 'ACTUALIZADO'
	}
	}, {
		freezeTableName: true, // Model tableName will be the same as the model name
		timestamps: false
});
Producto.belongsTo(Categoria,{ foreignKey: 'idCategoria'});
module.exports.Producto = Producto;