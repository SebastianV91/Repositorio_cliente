$(document).ready(()=>{
    
    const list = () => {

        $.ajax({
            url: 'http://localhost:8084/api/clientes',            
            type:'GET',
            datatype:'json',
            success: function(res){
                let data = '';
                res.forEach(element => {
                    data+= `
                        <tr alumnoId = ${element.id} >
                            <td>${element.id}</td>
                            <td>${element.primer_nombre}</td>
                            <td>${element.segundo_nombre}</td>
                            <td>${element.primer_apellido}</td>
                            <td>${element.segundo_apellido}</td>
                            <td>${element.nombEstado}</td>

                            <td>
                                <button id="btn-edit" class="btn btn-warning">PROCESAR</button>
                            </td>

                        </tr>
                    `
                    
                });
                
                $('#tbody').html(data);
    
            }
        })

    }

    const listProcesados = () => {

        $.ajax({
            url: 'http://localhost:8084/api/clientesProcesados',            
            type:'GET',
            datatype:'json',
            success: function(res){
                let data = '';
                res.forEach(element => {
                    data+= `
                        <tr alumnoId = ${element.id} >
                            <td>${element.id}</td>
                            <td>${element.primer_nombre}</td>
                            <td>${element.segundo_nombre}</td>
                            <td>${element.primer_apellido}</td>
                            <td>${element.segundo_apellido}</td>
                            <td>${element.nombEstado}</td>

                            <td>
                                <button id="btn-edit" class="btn btn-warning">PROCESAR</button>
                            </td>

                        </tr>
                    `
                    
                });
                
                $('#tbody').html(data);
    
            }
        })

    }

    const save = () => {
        $('#agregar').on('click', function(){
            const datosAlumno = {
                primer_nombre: $('#primer_nombre').val(),
                segundo_nombre: $('#segundo_nombre').val(),
                primer_apellido: $('#primer_apellido').val(),
                segundo_apellido: $('#segundo_apellido').val(),
                estado: $('#estado').val(),
            }

            $.ajax({
                url: 'http://localhost:8084/api/save',
                contentType: 'application/json',
                type: 'POST',
                data:JSON.stringify(datosAlumno),
                dataType: 'json',
                success: (data) => {
                    $('#messages').html('Cliente Creado').css('display','block')
                    reset();
                    console.log('Cliente registrado')
                }

            })

        })
    }
    
    const procesarCliente = () => {
        $(document).on('click', '#btn-edit', function(){

            let btnEdit = $(this)[0].parentElement.parentElement;
            let id = $(btnEdit).attr('alumnoId');
        
            const datosAlumno = {
                estado: $('#estado').val(),
            }

            $.ajax({
                url: 'http://localhost:8084/api/procesar/' + id,
                contentType: 'application/json',
                type: 'PUT',
                data: JSON.stringify(datosAlumno),
                dataType:'json',
                success: (res) => {

                    listProcesados();

                }
            })

        })


    }

    const reset = () => {
        $('#primer_nombre').val('');
        $('#segundo_nombre').val('');
        $('#primer_apellido').val('');
        $('#segundo_apellido').val('');
    }


    list();
    save();
    procesarCliente();
    listProcesados();

})