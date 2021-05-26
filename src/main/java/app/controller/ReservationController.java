package app.controller;

import app.ApplicationException;
import app.model.Reservation;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

@Controller
@RequestMapping("/reservation")
public class ReservationController extends app.controller.generic.Controller<Reservation> {
    @RequestMapping(path = "/image/{arg}")
    public String image(@PathVariable Integer arg, HttpServletResponse response, @Value("${local.server.port}") int port) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            String url = String.format("http://%s:%d/reservation/update/%d", host, port, arg);
            QRCode.from(url).writeTo(response.getOutputStream());
        } catch (IOException e) {
            throw new ApplicationException("Error generando la imagen", e);
        }
        return null;
    }
}
