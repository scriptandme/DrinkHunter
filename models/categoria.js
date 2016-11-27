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



var Categoria = sequelize.define('categoria', {
	id: {
		type: Sequelize.INTEGER,
		field: 'ID_CATEGORIA', // Will result in an attribute that is firstName when user facing but first_name in the database
		primaryKey: true,
		autoIncrement: true
	},
	nombre: {
		type: Sequelize.STRING,
		field: 'NOMBRE'
	},
	imagen: {
		type: Sequelize.STRING,
		field: 'IMAGEN'
	}
	}, {
		freezeTableName: true, // Model tableName will be the same as the model name
		timestamps: false
});

module.exports.Categoria = Categoria;