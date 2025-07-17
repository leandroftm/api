package med.voll.api.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.Address.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;
    private String neighborhood;
    private String cep;
    private String number;
    private String complement;
    private String city;
    private String fu;

    public Address(AddressDTO address) {
        this.address = address.address();
        this.neighborhood = address.neighborhood();
        this.cep = address.cep();
        this.number = address.number();
        this.complement = address.complement();
        this.city = address.city();
        this.fu = address.fu();
    }

    public void updateAddress(AddressDTO address) {
        if(address.neighborhood() != null)
            this.address = address.neighborhood();
        if(address.neighborhood() != null)
            this.neighborhood = address.neighborhood();
        if(address.cep() != null)
            this.cep = address.cep();
        if(address.number() != null)
            this.number = address.number();
        if(address.complement() != null)
            this.complement = address.complement();
        if(address.city() != null)
            this.city = address.city();
        if(address.fu() != null)
            this.fu = address.fu();
    }
}
