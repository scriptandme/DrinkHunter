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

var Producto = require('./producto').Producto
var Precio = sequelize.define('precio', {
	idPrecio: {
		type: Sequelize.INTEGER,
		field: 'ID_PRECIO', // Will result in an attribute that is firstName when user facing but first_name in the database
		primaryKey: true,
		autoIncrement: true
	},
	idProducto: {
		type: Sequelize.STRING,
		field: 'ID_PRODUCTO'
	},
    precio: {
		type: Sequelize.STRING,
		field: 'PRECIO'
	},
	oferta: {
		type: Sequelize.BOOLEAN,
		field: 'LINK'
	},
	bajo: {
		type: Sequelize.BOOLEAN,
		field: 'bajo'
	},
	fechaCarga: {
		type: Sequelize.DATE,
		field: 'FECHA_CARGA'
	}
	}, {
		freezeTableName: true, // Model tableName will be the same as the model name
		timestamps: false
});
Precio.belongsTo(Producto,{ foreignKey: 'idProducto'});
module.exports.Precio = Precio;