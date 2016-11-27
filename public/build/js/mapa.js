        var map;
        function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat: -33.433267, lng: -70.636003},
                    zoom: 15
                });

                var marker = new google.maps.Marker({
                    position: {lat: -33.435003, lng: -70.63098},
                    map: map,
                    title: 'Minimarket y Botillería'
                });

                var marker2 = new google.maps.Marker({
                    position: {lat: -33.4331784, lng: -70.6359272},
                    map: map,
                    title: 'Botillería'
                });


                var marker2 = new google.maps.Marker({
                    position: {lat: -33.4397396, lng: -70.6189509},
                    map: map,
                    title: 'Liquidos'
                });
        }