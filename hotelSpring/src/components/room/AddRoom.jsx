import React, { useState } from 'react'
import {addRoom} from "../utils/APIFunctions";

const AddRoom = () => {
  const [newRoom, setNewRoom] = useState({
    photo: null,
    roomType: "",
    roomPrice: ""
  });

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [imagePreview, setPreviewImage] = useState("");

  // use when user input new infor about room and information of room
  const handleInputChange = (e) => {
    const name = e.target.name;
    let value = e.target.value;
    if(name === 'roomPrice') {
      if(!isNaN(value)) {
        value = parseInt(value);
      } else {
        value = "";
      }
    }
    setNewRoom({...newRoom, [name]: value});  // this one will change only the property we are target to
  }

  const handleImageChange = (e) => {
    const selectedImage = e.target.files[0];
    setNewRoom({...newRoom, photo: selectedImage});
    setPreviewImage(URL.createObjectURL(selectedImage));
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const success = await addRoom(newRoom.photo, newRoom.roomType, newRoom.roomPrice);
      if(success !== undefined) {
        // succes => reset all properties
        setSuccessMessage("Adding a new Room successfully")
        setNewRoom({
          photo: null,
          roomType: "",
          roomPrice: ""
        });
        setPreviewImage("");
        setErrorMessage("");
      } else {
        setErrorMessage("Error adding a new Room");
      }
    } catch (error) {
      setErrorMessage(error.message);
    }
    // after 3 seconds notification will disappear
    setTimeout(() => {
      setSuccessMessage("");
      setErrorMessage("");
    }, 3000);
  }

  return (
    <>
      <section className='container mt-5 mb-5'>
        <div className='row justify-content-center'>
          <div className='col-md-8 col-lg-6'>
            <h2 className='mt-5 mb-2'>Add a new Room</h2>
              {successMessage && (
                <div className='alert alert-success fade show'>{successMessage}</div>
              )}
              {errorMessage && <div className='alert alert-danger fade show'>{errorMessage}</div>}
          </div>
        </div>
      </section>
    </>
  )
}

export default AddRoom