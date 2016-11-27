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


var Local = sequelize.define('local', {
	idLocal: {
		type: Sequelize.INTEGER,
		field: 'ID_LOCAL', // Will result in an attribute that is firstName when user facing but first_name in the database
		primaryKey: true,
		autoIncrement: true
	},
	imagen: {
		type: Sequelize.STRING,
		field: 'imagen'
	},
	nombre: {
		type: Sequelize.STRING,
		field: 'NOMBRE'
	},
	imagen: {
		type: Sequelize.STRING,
		field: 'IMAGEN'
	},
	lat: {
		type: Sequelize.DOUBLE
	},
	long: {
		type: Sequelize.DOUBLE
	},
	tipo: {
		type: Sequelize.STRING,
		field: 'TIPO'
	},
	url: {
		type: Sequelize.INTEGER,
		field: 'URL'
	}
	}, {
		freezeTableName: true, // Model tableName will be the same as the model name
		timestamps: false
});
module.exports.Local = Local;