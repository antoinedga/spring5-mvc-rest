package guru.springfamework.api.v1.mappers;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.domain.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDtoToVendor(VendorDTO vendorDTO);

}
