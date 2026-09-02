
function App() {

  return (
    <>
     <div className="relative w-full flex justify-center min-h-screen" >
      <img src="/logo.png" className="h-15 w-fit absolute left-10 text-amber-100 top-5 " alt="" />
      <div className="absolute w-3xl items-center flex justify-around rounded-3xl backdrop-blur-3xl bg-white/20 h-13 bottom-10 ">
      <div className="bg-amber-900 h-[75%] w-2/5 rounded-3xl " ></div>
      <div className="bg-amber-950 h-[75%] w-3/5 rounded-3xl " ></div>
      </div>
    </div>
    </>
  )
}

export default App
