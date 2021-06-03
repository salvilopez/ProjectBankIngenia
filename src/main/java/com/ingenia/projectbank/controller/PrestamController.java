package com.ingenia.projectbank.controller;

import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.Prestam;
import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.service.PrestamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://bank-ingenia-front.vercel.app", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PrestamController {
    private final Logger log = LoggerFactory.getLogger(PrestamController.class);
    private final PrestamService prestamService;

    public PrestamController(PrestamService prestamService) {
        this.prestamService = prestamService;
    }


    /**
     * method return all BankCards
     * @return List<Prestams>
     */
    @GetMapping("/prestams")
    @ApiOperation(value = "encuentra todas los Prestamos ")
    public List<Prestam> findAllPrestams(){
        log.debug("Rest request all prestams");
        return prestamService.findAllPrestams();
    }


    /**
     * Find  prestan by id
     * @param id
     * @return
     */
    @GetMapping("/prestam/{id}")
    @ApiOperation(value = "encuentra un Prestamo por su id")
    public ResponseEntity<Prestam> findOnePrestam(@ApiParam("Clave primaria de Prestamo") @PathVariable Long id) {
        log.debug("Rest request a Prestam with id: "+id);
        Optional<Prestam> restamOpt=prestamService.findOnePrestamById(id);

            return ResponseEntity.ok().body(restamOpt.get());

    }



    /**
     *method return all Prestam By User id
     * @param id Long
     * @return
     */
    @GetMapping("/prestam-user-id/{id}")
    @ApiOperation(value = "encuentrar Prestam por id")
    public ResponseEntity <List<Prestam>> findPrestamByUserId(@ApiParam("Id Prestam a consultar")@PathVariable Long id) {
        if(id!=null){
            log.debug("Rest request  Prestam of user: "+ id);
            List<Prestam> prestamList = prestamService.findPrestamsByUserId(id);
                return ResponseEntity.ok().body(prestamList);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     *
     * @param prestam
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/prestam")
    @ApiOperation(value = "crea una Prestam")
    public ResponseEntity<Prestam> createPrestam(@ApiParam("Objeto Prestam para Crearlo")@RequestBody Prestam prestam) throws URISyntaxException{
        log.debug("Create Prestam");
        Prestam resultado=null;
        if (prestam.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado=prestamService.createPrestam(prestam);
        return  ResponseEntity.created(new URI("/api/prestam/"+resultado.getId())).body(resultado);
    }

    /**
     * Modificar BankCard
     * Modify BankCard
     * @param prestam
     * @return
     */
    @PutMapping(value = "/prestam")
    @ApiOperation(value = "modificar  Prestam")
    public ResponseEntity<Prestam> modifyBankCard(@ApiParam("Objeto Prestam a modificar")@RequestBody Prestam prestam){
        log.debug("Modify bankCard");
        Prestam resultado=null;
        if (prestam.getId()==null) {
            log.warn("Updating bankCard without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        resultado = prestamService.updatePrestam(prestam);
        return ResponseEntity.ok().body(resultado);
    }


    /**
     * delete by id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/prestam/{id}")
    @ApiOperation(value = "Borra una  tarjeta bancaria por id")
    public ResponseEntity<Void> deleteOne(@ApiParam("Clave primaria de la Prestam")@PathVariable("id") Long id) {
        log.debug("Delete Prestam");
        if(id!=null){
            prestamService.deleteOnePrestamById(id);
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * delete all Prestam
     * @return
     */
    @ApiOperation(value = "Borra todas las Prestam")
    @DeleteMapping(value = "/prestams")
    public ResponseEntity<Void> deleteAll() {
        log.debug("DeleteAllPrestam");
        prestamService.deleteAllPrestams();
        return  ResponseEntity.noContent().build();
    }
}
